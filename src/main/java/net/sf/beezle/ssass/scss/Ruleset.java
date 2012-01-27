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
        Declaration.toCss(selectors, ssassDeclarations, output);
    }
}
