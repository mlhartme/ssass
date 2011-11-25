package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Exs extends BaseTerm {
    private String emx;

    public Exs(String emx) {
        this.emx = emx;
    }

    @Override
    public void toCss(Output output) {
        output.string(emx);
    }
}
