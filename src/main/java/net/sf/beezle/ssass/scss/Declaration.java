package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

import java.util.ArrayList;
import java.util.List;

public class Declaration extends Base implements NestedDeclaration {
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

    public static void toCss(Selector[] context, NestedDeclaration[] nestedDeclarations, Output output) throws GenericException {
        boolean first;
        List<Ruleset> rulesets;

        rulesets = new ArrayList<Ruleset>();
        output.open();
        first = true;
        for (NestedDeclaration nestedDeclaration : nestedDeclarations) {
            if (first) {
                first = false;
            } else {
                output.semicolon();
            }
            if (nestedDeclaration instanceof Declaration) {
                ((Declaration) nestedDeclaration).toCss(output);
            } else {
                rulesets.add((Ruleset) nestedDeclaration);
            }
        }
        output.semicolonOpt();
        output.close();
        output.push(context);
        for (Ruleset ruleset : rulesets) {
            ruleset.toCss(output);
        }
        output.pop();
    }
}
