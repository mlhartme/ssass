package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Angle implements BaseTerm {
    private final String angle;

    public Angle(String angle) {
        this.angle = angle;
    }

    @Override
    public void toCss(Output output) {
        output.string(angle);
    }
}
