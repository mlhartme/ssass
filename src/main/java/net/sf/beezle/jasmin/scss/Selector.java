package net.sf.beezle.jasmin.scss;

public class Selector {
    private final SimpleSelector left;

    /* may be null */
    private final Combinator combinator;
    private final Selector right;

    public Selector(SimpleSelector left, Combinator combinator, Selector right) {
        this.left = left;
        this.combinator = combinator;
        this.right = right;
    }
}
