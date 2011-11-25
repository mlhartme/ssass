package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Time extends BaseTerm {
    private final String time;

    public Time(String time) {
        this.time = time;
    }

    @Override
    public void toCss(Output output) {
        output.string(time);
    }
}
