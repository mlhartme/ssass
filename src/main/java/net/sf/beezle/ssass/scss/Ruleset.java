package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Ruleset implements Statement, NestedDeclaration {
    private Selector[] selectors;
    private NestedDeclaration[] nestedDeclarations;

    // TODO: NestedDeclaration[] nestedDeclarations
    public Ruleset(Selector[] selectors, Base[] nestedDeclarations) {
        this.selectors = selectors;
        this.nestedDeclarations = new NestedDeclaration[nestedDeclarations.length];
        for (int i = 0; i < nestedDeclarations.length; i++) {
            this.nestedDeclarations[i] = (NestedDeclaration) nestedDeclarations[i];
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        first = true;
        for (Selector[] context : output.nested) {
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
        Declaration.toCss(selectors, nestedDeclarations, output);
    }
}
