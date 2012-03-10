package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

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
        output.object(left, combinator == null && right != null ? " " : combinator, right);
    }
}
