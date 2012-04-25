package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Exs implements BaseTerm {
    private String emx;

    public Exs(String emx) {
        this.emx = emx;
    }

    @Override
    public void toCss(Output output) {
        if (output.compress() && "0ex".equalsIgnoreCase(emx)) {
            output.string("0");
        } else {
            output.string(emx);
        }
    }
}
