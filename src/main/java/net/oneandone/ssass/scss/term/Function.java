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
package net.oneandone.ssass.scss.term;

import net.oneandone.mork.misc.GenericException;
import net.oneandone.ssass.scss.Expr;
import net.oneandone.ssass.scss.Output;
import net.oneandone.sushi.util.Strings;

public class Function implements BaseTerm {
    public final String name;
    public final Expr expr;

    public Function(String nameOpen, Expr expr) {
        this.name = Strings.removeRight(nameOpen, "(");
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(name, "(", expr, ")");
    }
}
