package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Selector implements Base {
    private final SimpleSelector left;

    /* may be null */
    private final Combinator combinator;
    private final Selector right;

    public Selector(SimpleSelector left, Combinator combinator, Selector right) {
        this.left = left;
        this.combinator = combinator;
        this.right = right;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(left, combinator == null && right != null ? " " : combinator, right);
    }
}
