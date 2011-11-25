package net.sf.beezle.ssass.scss;

public class SimpleSelector extends Base {
    private BaseSelector[] bases;

    public SimpleSelector(ElementName first, BaseSelector[] bases) {
        this.bases = new BaseSelector[bases.length + 1];
        this.bases[0] = first;
        System.arraycopy(bases, 0, this.bases, 1, bases.length);
    }

    @Override
    public void toCss(Output output) {
        output.base(bases);
    }
}
