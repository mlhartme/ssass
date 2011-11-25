package net.sf.beezle.ssass.scss;

public class UnaryOperator {
    public static final UnaryOperator PLUS = new UnaryOperator('+');
    public static final UnaryOperator MINUS = new UnaryOperator('-');

    private final char code;

    private UnaryOperator(char code) {
        this.code = code;
    }

    public String toString() {
        return String.valueOf(code);
    }
}
