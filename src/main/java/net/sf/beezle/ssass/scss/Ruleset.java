package net.sf.beezle.ssass.scss;

public class Ruleset extends Statement {
    private Selector[] selectors;
    private Declaration[] declarations;

    public Ruleset(Selector[] selectors, Declaration[] declarations) {
        this.selectors = selectors;
        this.declarations = declarations;
    }

    @Override
    public void toCss(Output output) {
        boolean first;

        first = true;
        for (Selector selector : selectors) {
            if (first) {
                first = false;
            } else {
                output.string(", ");
            }
            selector.toCss(output);
        }
        output.string(" {\n");
        first = true;
        for (Declaration declaration : declarations) {
            if (first) {
                first = false;
            } else {
                output.string(";\n");
            }
            declaration.toCss(output);
        }
        output.string("\n}");
    }
}
