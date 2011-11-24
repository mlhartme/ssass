package net.sf.beezle.ssass.scss;

public class Operator {
    public static final Operator SLASH = new Operator('/');
    public static final Operator COMMA = new Operator(',');

    private final char code;

    private Operator(char code) {
        this.code = code;
    }
}
