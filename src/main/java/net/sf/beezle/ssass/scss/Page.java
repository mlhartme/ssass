package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Page extends Statement {
    // may be null
    private final String pseudoPage;

    private final Declaration[] declarations;

    public Page(String pseudoPage, Declaration[] declarations) {
        this.pseudoPage = pseudoPage;
        this.declarations = declarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.string("@page");
        if (pseudoPage != null) {
            output.string(" :", pseudoPage);
        }
        Declaration.toCss(declarations, output);
    }
}
