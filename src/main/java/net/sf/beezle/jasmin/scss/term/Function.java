package net.sf.beezle.jasmin.scss.term;

import net.sf.beezle.jasmin.scss.Expr;

public class Function extends BaseTerm {
    private final String name;
    private final Expr expr;

    public Function(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }
}
