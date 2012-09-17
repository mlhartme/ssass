/**
 * Copyright 1&1 Internet AG, http://www.1and1.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
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
        this.ops = new ArrayList<Operator>();
        this.terms = new ArrayList<Term>();
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

        if (output.compress() && fourInOne()) {
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

    private boolean fourInOne() {
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
