package net.sf.beezle.jasmin.scss;

public class Expr {
    private final Term left;
    private final Operator[] ops;
    private final Term[] rights;

    public Expr(Term left, Operator[] ops, Term[] rights) {
        this.left = left;
        this.ops = ops;
        this.rights = rights;
    }
}
