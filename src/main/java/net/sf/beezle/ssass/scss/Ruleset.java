package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Ruleset extends Statement {
    private Selector[] selectors;
    private Declaration[] declarations;

    public Ruleset(Selector[] selectors, Declaration[] declarations) {
        this.selectors = selectors;
        this.declarations = declarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        first = true;
        for (Selector selector : selectors) {
            if (first) {
                first = false;
            } else {
                output.string(",");
                output.spaceOpt();
            }
            selector.toCss(output);
        }
        Declaration.toCss(declarations, output);
    }
}
