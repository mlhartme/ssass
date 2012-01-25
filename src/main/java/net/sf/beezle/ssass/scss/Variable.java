package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Variable implements Statement {
    private final String name;
    private final Expr expr;

    public Variable(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.set(this);
    }

    public String getName() {
        return name;
    }

    public Expr getExpr() {
        return expr;
    }
}
