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

public class SimpleSelectorSequence implements Base {
    private BaseSelector[] bases;

    public SimpleSelectorSequence(TypeSelector first, BaseSelector[] bases) {
        if (first == null) {
            this.bases = bases;
        } else {
            this.bases = new BaseSelector[bases.length + 1];
            this.bases[0] = first;
            System.arraycopy(bases, 0, this.bases, 1, bases.length);
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.base(bases);
    }
}
