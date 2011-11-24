package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Strng extends BaseTerm {
    private final String str;

    public Strng(String str) {
        this.str = str;
    }

    @Override
    public void toCss(Output output) {
        // TODO
    }
}
