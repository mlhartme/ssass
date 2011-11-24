package net.sf.beezle.ssass.scss;

import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Term {
    private final UnaryOperator unary;
    private final BaseTerm baseTerm;

    public Term(BaseTerm baseTerm) {
        this(null, baseTerm);
    }

    public Term(UnaryOperator unary, BaseTerm baseTerm) {
        this.unary = unary;
        this.baseTerm = baseTerm;
    }
}
