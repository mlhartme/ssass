package net.sf.beezle.ssass.scss;

import java.util.ArrayList;
import java.util.List;

public class Expr extends Base {
    private final Object[] opsOrTerms;  // TODO: redundant
    private final List<Operator> ops;
    private final List<Term> terms;

    // TODO
    public Expr(Object[] opsOrTerms) {
        Object obj;

        this.opsOrTerms = opsOrTerms;
        this.ops = new ArrayList<Operator>();
        this.terms = new ArrayList<Term>();
        for (int i = 0; i < opsOrTerms.length; i++) {
            obj = opsOrTerms[i];
            if (obj instanceof Term) {
                ops.add(null);
                terms.add((Term) obj);
            } else if (obj instanceof Operator) {
                ops.add((Operator) obj);
                terms.add((Term) opsOrTerms[++i]);
            } else {
                throw new IllegalStateException(obj.getClass().getName());
            }
        }
    }

    @Override
    public void toCss(Output output) {
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                output.base((Term) obj);
            } else if (obj instanceof Operator) {
                output.string(obj.toString());
            } else {
                throw new IllegalArgumentException("" + obj);
            }
        }
    }
}
