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
package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Variable implements Statement {
    private final String name;
    private final Expr expr;

    public Variable(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.defineVariable(this);
    }

    public String getName() {
        return name;
    }

    public Expr getExpr() {
        return expr;
    }
}
