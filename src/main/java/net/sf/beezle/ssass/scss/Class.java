package net.sf.beezle.ssass.scss;

public class Class implements BaseSelector {
    private final String name;

    public Class(String name) {
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        output.string(".", name);
    }
}
