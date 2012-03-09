package net.sf.beezle.ssass.scss;

public class MediaOperator {
    public static final MediaOperator NOT = new MediaOperator("not");
    public static final MediaOperator ONLY = new MediaOperator("only");

    private final String str;

    private MediaOperator(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
