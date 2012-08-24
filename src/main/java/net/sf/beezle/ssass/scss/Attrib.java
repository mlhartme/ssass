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

public class Attrib implements BaseSelector {
    private final TypeSelector typeSelector;
    private final AttribOp op;
    private final String value;

    public Attrib(TypeSelector typeSelector, AttribOp op, String value) {
        this.typeSelector = typeSelector;
        this.op = op;
        this.value = value;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object("[", typeSelector, op.toString(), value, "]");
    }
}
