package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Expr;
import net.sf.beezle.ssass.scss.Output;

public class Function extends BaseTerm {
    private final String name;
    private final Expr expr;

    public Function(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) {
        output.object(name, expr, ")");
    }
}
