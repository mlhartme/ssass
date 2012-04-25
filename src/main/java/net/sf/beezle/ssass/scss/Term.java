package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.term.BaseTerm;

public class Term implements Base {
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

    public boolean isZero() {
        if (unary == null) {
            if (baseTerm instanceof net.sf.beezle.ssass.scss.term.Number) {
                return ((net.sf.beezle.ssass.scss.term.Number) baseTerm).isZero();
            }
            if (baseTerm instanceof net.sf.beezle.ssass.scss.term.Length) {
                return ((net.sf.beezle.ssass.scss.term.Length) baseTerm).isZero();
            }
        }
        return false;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        if (unary != null) {
            output.string(unary.toString());
        }
        baseTerm.toCss(output);
    }
}
