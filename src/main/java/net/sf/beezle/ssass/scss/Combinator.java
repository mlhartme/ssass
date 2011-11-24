package net.sf.beezle.ssass.scss;

public class Combinator {
    public static final Combinator PLUS = new Combinator('+');
    public static final Combinator GT = new Combinator('>');

    private final char code;

    private Combinator(char code) {
        this.code = code;
    }
}
