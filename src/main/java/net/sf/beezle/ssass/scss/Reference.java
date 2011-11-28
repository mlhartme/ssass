package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Reference extends BaseTerm {
    private final Variable variable;

    public Reference(Variables variables, String name) throws GenericException {
        this.variable = variables.resolve(name);
    }

    @Override
    public void toCss(Output output) {
        variable.getTerm().toCss(output);
    }
}
