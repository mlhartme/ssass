package net.sf.beezle.ssass.scss;

public class ElementName implements BaseSelector {
    private final String name;

    public ElementName(String name) {
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        output.string(name);
    }
}
