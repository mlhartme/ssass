package net.sf.beezle.ssass.scss;

public class Prio extends Base {
    @Override
    public void toCss(Output output) {
        output.string(" !important");
    }
}
