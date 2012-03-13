package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Negation implements BaseSelector {
    private final BaseSelector arg;

    public Negation(BaseSelector arg) {
        this.arg = arg;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object("not(", arg, ")");
    }
}
