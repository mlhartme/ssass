package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Resolution implements BaseTerm {
    private final String resolution;

    public Resolution(String resolution) {
        this.resolution = resolution;
    }

    @Override
    public void toCss(Output output) {
        output.string(resolution);
    }
}
