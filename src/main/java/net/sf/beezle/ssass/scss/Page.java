package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Page implements Statement {
    // may be null
    private final String pseudoPage;

    private final SsassDeclaration[] declarations;

    public Page(String pseudoPage, SsassDeclaration[] declarations) {
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
