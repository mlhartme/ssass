package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Reference extends BaseTerm {
    private final String name;

    public Reference(String name) throws GenericException {
        this.name = name;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Variable variable;

        variable = output.lookup(name);
        if (variable == null) {
            throw new GenericException("undefined variable: " + name);
        }
        variable.getExpr().toCss(output);
    }
}
