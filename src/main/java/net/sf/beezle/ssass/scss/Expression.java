package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Expression implements Base {
    public static final String PLUS = "+";
    public static final String MINUS = "-";

    private final Object[] args;

    public Expression(Object[] args) {
        this.args = args;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(args);
    }
}
