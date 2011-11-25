package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Number extends BaseTerm {
    private final String num;

    public Number(String num) {
        this.num = num;
    }

    @Override
    public void toCss(Output output) {
        output.string(num);
    }
}
