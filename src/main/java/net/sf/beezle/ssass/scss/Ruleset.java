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
        boolean first;

        first = true;
        for (Selector[] context : output.selectorContext) {
            for (Selector selector : context) {
                if (first) {
                    first = false;
                } else {
                    output.string(",");
                    output.spaceOpt();
                }
                selector.toCss(output);
            }
        }
        for (Selector selector : selectors) {
            if (first) {
                first = false;
            } else {
                output.string(",");
                output.spaceOpt();
            }
            selector.toCss(output);
        }
        toCss(selectors, ssassDeclarations, output);
    }

    private static final Selector[] NO_SELECTORS = new Selector[0];

    public static void toCss(SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        toCss(NO_SELECTORS, ssassDeclarations, output);
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
