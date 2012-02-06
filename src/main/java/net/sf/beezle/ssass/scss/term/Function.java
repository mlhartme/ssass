package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.Expr;
import net.sf.beezle.ssass.scss.Output;

public class Function implements BaseTerm {
    private final String name;
    private final Expr expr;

    public Function(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(name, "(", expr, ")");
    }
}
