package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Percentage implements BaseTerm {
    private final String percentage;

    public Percentage(String percentage) {
        this.percentage = percentage;
    }

    @Override
    public void toCss(Output output) {
        if (output.compress() && "0%".equals(percentage)) {
            output.string("0");
        } else {
            output.string(percentage);
        }
    }
}
