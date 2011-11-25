package net.sf.beezle.ssass.scss;

public class Declaration extends Base {
    private final String property;
    private final Expr expr;
    private final Prio prio;

    public Declaration(String property, Expr expr, Prio prio) {
        this.property = property;
        this.expr = expr;
        this.prio = prio;
    }

    @Override
    public void toCss(Output output) {
        output.object(property, ": ", expr, prio);
    }
}
