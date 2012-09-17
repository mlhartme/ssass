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
