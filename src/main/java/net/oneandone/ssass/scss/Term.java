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
import net.oneandone.ssass.scss.term.BaseTerm;

public class Term implements Base {
    /** may be null */
    private final UnaryOperator unary;
    private final BaseTerm baseTerm;

    public Term(BaseTerm baseTerm) {
        this(null, baseTerm);
    }

    public Term(UnaryOperator unary, BaseTerm baseTerm) {
        this.unary = unary;
        this.baseTerm = baseTerm;
    }

    public boolean isZero() {
        if (unary == null) {
            if (baseTerm instanceof net.oneandone.ssass.scss.term.Number) {
                return ((net.oneandone.ssass.scss.term.Number) baseTerm).isZero();
            }
            if (baseTerm instanceof net.oneandone.ssass.scss.term.Length) {
                return ((net.oneandone.ssass.scss.term.Length) baseTerm).isZero();
            }
        }
        return false;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean compress;

        compress = output.compress();
        if (unary != null) {
            output.string(unary.toString());
            output.setCompress(false);
        }
        baseTerm.toCss(output);
        output.setCompress(compress);
    }
}
