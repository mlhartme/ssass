package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Ident implements BaseTerm {
    public final String name;

    public Ident(String name) {
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        output.string(name);
    }
}
