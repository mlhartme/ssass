package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Freq implements BaseTerm {
    private final String freq;

    public Freq(String freq) {
        this.freq = freq;
    }

    @Override
    public void toCss(Output output) {
        output.string(freq);
    }
}