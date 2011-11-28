package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Variables extends Base {
    private Variable[] variables;

    public Variables(Variable[] variables) {
        this.variables = variables;
    }

    public Variable resolve(String name) throws GenericException {
        for (Variable var : variables) {
            if (name.equals(var.getName())) {
                return var;
            }
        }
        throw new GenericException("undefined variable: " + name);
    }

    @Override
    public void toCss(Output output) {
        // nothing
    }
}
