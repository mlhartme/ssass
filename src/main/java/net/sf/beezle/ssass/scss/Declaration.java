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
        output.object(property, ":");
        output.spaceOpt();
        output.object(expr);
        if (prio != null) {
            output.spaceOpt();
            output.base(prio);
        }
    }

    public static void toCss(Declaration[] declarations, Output output) {
        boolean first;

        output.open();
        first = true;
        for (Declaration declaration : declarations) {
            if (first) {
                first = false;
            } else {
                output.semicolon();
            }
            declaration.toCss(output);
        }
        output.semicolonOpt();
        output.close();
    }
}
