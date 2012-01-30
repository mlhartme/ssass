package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Ruleset implements Statement, SsassDeclaration {
    private Selector[] selectors;
    private SsassDeclaration[] ssassDeclarations;

    public Ruleset(Selector[] selectors, SsassDeclaration[] ssassDeclarations) {
        this.selectors = selectors;
        this.ssassDeclarations = ssassDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.selectors(selectors);
        toCss(selectors, ssassDeclarations, output);
    }

    private static final Selector[] NO_SELECTORS = new Selector[0];

    public static void toCss(SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        toCss(NO_SELECTORS, ssassDeclarations, output);
    }

    public static void toCss(Selector[] context, SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        boolean first;

        output.pushSelector(context);
        output.open();
        first = true;
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (ssassDeclaration instanceof Ruleset) {
                output.delay((Ruleset) ssassDeclaration);
            } else {
                if (first) {
                    first = false;
                } else {
                    output.semicolon();
                }
                ssassDeclaration.toCss(output);
            }
        }
        output.semicolonOpt();
        output.close();
        output.popSelector();

        if (output.isTopLevel()) {
            output.delayed();
        }
    }
}
