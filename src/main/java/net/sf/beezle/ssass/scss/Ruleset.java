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
        // TODO
    }
}
