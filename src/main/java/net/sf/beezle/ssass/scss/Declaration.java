package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

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
    public void toCss(Output output) throws GenericException {
        output.object(property, ":");
        output.spaceOpt();
        output.object(expr);
        if (prio != null) {
            output.spaceOpt();
            output.base(prio);
        }
    }

    public static void toCss(Declaration[] declarations, Output output) throws GenericException {
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
