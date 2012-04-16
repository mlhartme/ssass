package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Number implements BaseTerm {
    private final String num;

    public Number(String num) {
        this.num = num;
    }

    public boolean isZero() {
        return "0".equals(num);
    }

    @Override
    public void toCss(Output output) {
        output.string(num);
    }
}
