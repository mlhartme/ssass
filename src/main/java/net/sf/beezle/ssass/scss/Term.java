package net.sf.beezle.ssass.scss;

import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Term extends Base {
    /** may be null */
    private final UnaryOperator unary;
    private final BaseTerm baseTerm;

    public Term(BaseTerm baseTerm) {
        this(null, baseTerm);
    }

    public Term(UnaryOperator unary, BaseTerm baseTerm) {
        this.unary = unary;
        this.baseTerm = baseTerm;
    }

    @Override
    public void toCss(Output output) {
        if (unary != null) {
            output.string(unary.toString());
        }
        baseTerm.toCss(output);
    }
}
