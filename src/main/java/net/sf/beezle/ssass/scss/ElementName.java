package net.sf.beezle.ssass.scss;

public class ElementName extends BaseSelector {
    private final String name;

    public ElementName(String name) {
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        // TODO
    }
}
