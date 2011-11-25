package net.sf.beezle.ssass.scss;

public class Selector extends Base {
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
    public void toCss(Output output) {
        output.object(left, combinator, right);
    }
}
