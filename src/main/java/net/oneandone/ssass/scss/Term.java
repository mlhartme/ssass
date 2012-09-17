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
