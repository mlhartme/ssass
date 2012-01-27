package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Declaration implements Base, SsassDeclaration {
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
        output.propertyPrefix();
        output.object(property, ":");
        output.spaceOpt();
        output.object(expr);
        if (prio != null) {
            output.spaceOpt();
            output.base(prio);
        }
    }

    public static void toCss(Selector[] context, SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        boolean first;

        output.open();
        first = true;
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (first) {
                first = false;
            } else {
                output.semicolon();
            }
            if (!(ssassDeclaration instanceof Ruleset)) {
                ssassDeclaration.toCss(output);
            }
        }
        output.semicolonOpt();
        output.close();
        output.pushSelector(context);
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (ssassDeclaration instanceof Ruleset) {
                ssassDeclaration.toCss(output);
            }
        }
        output.popSelector();
    }
}
