package net.sf.beezle.ssass.scss;

public class Page extends Statement {
    // may be null
    private final String pseudoPage;

    private final Declaration[] declarations;

    public Page(String pseudoPage, Declaration[] declarations) {
        this.pseudoPage = pseudoPage;
        this.declarations = declarations;
    }

    @Override
    public void toCss(Output output) {
        boolean first;

        output.string("@page");
        if (pseudoPage != null) {
            output.string(" :", pseudoPage);
        }
        output.open();
        first = true;
        for (Declaration declaration : declarations) {
            if (first) {
                first = false;
            } else {
                output.string(";\n");
            }
            declaration.toCss(output);
        }
        output.string("\n");
        output.close();
    }
}
