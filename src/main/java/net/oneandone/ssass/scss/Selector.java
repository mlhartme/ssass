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

public class Selector implements Base {
    public static Selector[] cross(Selector[] lefts, Selector[] rights) {
        List<Selector> lst;
        Selector[] result;

        lst = new ArrayList<Selector>();
        for (Selector left : lefts) {
            for (Selector right : rights) {
                lst.add(left.append(right));
            }
        }
        result = new Selector[lst.size()];
        lst.toArray(result);
        return result;
    }

    private final SimpleSelectorSequence left;

    /* null when right == null or to represent " " */
    private final Combinator combinator;

    /* may be null */
    private final Selector right;

    public Selector(SimpleSelectorSequence left, Combinator combinator, Selector right) {
        this.left = left;
        this.combinator = combinator;
        this.right = right;
    }

    /** lisp-like list-append */
    public Selector append(Selector tail) {
        if (right == null) {
            if (combinator != null) {
                throw new IllegalStateException();
            }
            return new Selector(left, null, tail);
        } else {
            return new Selector(left, combinator, right.append(tail));
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(left);
        if (right != null) {
            output.object(combinator == null ? " " : combinator.toString(), right);
        } else {
            if (combinator != null) {
                throw new IllegalStateException();
            }
        }
    }
}
