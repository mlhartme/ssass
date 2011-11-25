package net.sf.beezle.ssass.scss;

public class Output {
    private final StringBuilder builder;

    public Output() {
        builder = new StringBuilder();
    }

    public void write(String str) {
        builder.append(str);
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
