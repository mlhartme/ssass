package net.sf.beezle.ssass.scss;

public class Page extends Statement {
    // may be null
    private final String pseudoPage;

    private final Declaration[] declarations;

    public Page(String pseudoPage, Declaration[] declarations) {
        this.pseudoPage = pseudoPage;
        this.declarations = declarations;
    }
}
