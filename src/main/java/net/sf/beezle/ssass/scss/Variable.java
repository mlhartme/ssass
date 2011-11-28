package net.sf.beezle.ssass.scss;

import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Variable extends Base {
    private final String name;
    private final Term term;

    public Variable(String name, BaseTerm baseTerm) {
        this.name = name;
        this.term = new Term(baseTerm);
    }

    @Override
    public void toCss(Output output) {
        // nothing
    }

    public String getName() {
        return name;
    }

    public Term getTerm() {
        return term;
    }
}
