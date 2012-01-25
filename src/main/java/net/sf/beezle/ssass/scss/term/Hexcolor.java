package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Hexcolor implements BaseTerm {
    private final String hexcolor;

    public Hexcolor(String hexcolor) {
        this.hexcolor = hexcolor;
    }

    @Override
    public void toCss(Output output) {
        output.string(hexcolor);
    }
}
