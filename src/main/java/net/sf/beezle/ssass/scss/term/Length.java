package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Length implements BaseTerm {
    private final String len;

    public Length(String len) {
        this.len = len;
    }

    @Override
    public void toCss(Output output) {
        if (output.compress() && isZero()) {
            output.string("0");
        } else {
            output.string(len);
        }
    }

    public boolean isZero() {
        return len.length() == 3 && len.charAt(0) == '0';
    }
}
