package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Reference implements BaseTerm {
    private final String name;

    public Reference(String name) throws GenericException {
        this.name = name;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Expr value;

        value = output.lookupVariable(name);
        if (value == null) {
            throw new GenericException("undefined variable: " + name);
        }
        value.toCss(output);
    }
}
