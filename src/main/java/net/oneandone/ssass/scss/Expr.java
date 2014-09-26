/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.oneandone.ssass.scss;

import net.oneandone.mork.misc.GenericException;

import java.util.ArrayList;
import java.util.List;

public class Expr implements Base {
    private final Object[] opsOrTerms;  // TODO: redundant
    private final List<Operator> ops;
    private final List<Term> terms;

    // TODO
    public Expr(Object[] opsOrTerms) {
        Object obj;

        this.opsOrTerms = opsOrTerms;
        this.ops = new ArrayList<>();
        this.terms = new ArrayList<>();
        for (int i = 0; i < opsOrTerms.length; i++) {
            obj = opsOrTerms[i];
            if (obj instanceof Term) {
                ops.add(null);
                terms.add((Term) obj);
            } else if (obj instanceof Operator) {
                ops.add((Operator) obj);
                terms.add((Term) opsOrTerms[++i]);
            } else {
                throw new IllegalStateException(obj.getClass().getName());
            }
        }
    }

    public void toArguments(List<Expr> result) throws GenericException {
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                result.add(new Expr(new Object[] { obj }));
            } else if (obj != Operator.COMMA) {
                throw new GenericException("argument list expected");
            }
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Object last;

        if (output.compress() && !"background-position".equals(output.getDeclaration()) && intoOne()) {
            ((Term) opsOrTerms[0]).toCss(output);
            return;
        }
        last = null;
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                if (last != null && !(last instanceof Operator)) {
                    output.string(" ");
                }
                output.base((Term) obj);
            } else if (obj instanceof Operator) {
                output.string(obj.toString());
            } else {
                throw new IllegalArgumentException("" + obj);
            }
            last = obj;
        }
    }

    private boolean intoOne() {
        int terms;

        terms = 0;
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                terms++;
                if (!((Term) obj).isZero()) {
                    return false;
                }
            } else if (obj instanceof Operator) {
                return false;
            } else {
                throw new IllegalArgumentException("" + obj);
            }
        }
        return terms > 1 && terms < 5;
    }
}
