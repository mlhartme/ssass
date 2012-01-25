package net.sf.beezle.ssass.scss;

public class Prio implements Base {
    @Override
    public void toCss(Output output) {
        output.string("!important");
    }
}
