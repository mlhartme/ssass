package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

import java.util.ArrayList;
import java.util.List;

public class Expr implements Base {
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

    public void toArguments(List<Expr> result) throws GenericException {
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                result.add(new Expr(new Object[] { obj }));
            } else if (obj != Operator.COMMA) {
                throw new GenericException("argument list expected");
            }
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Object last;

        if (output.compress() && fourInOne()) {
            ((Term) opsOrTerms[0]).toCss(output);
            return;
        }
        last = null;
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                if (last != null && !(last instanceof Operator)) {
                    output.string(" ");
                }
                output.base((Term) obj);
            } else if (obj instanceof Operator) {
                output.string(obj.toString());
            } else {
                throw new IllegalArgumentException("" + obj);
            }
            last = obj;
        }
    }

    private boolean fourInOne() {
        int terms;

        terms = 0;
        for (Object obj : opsOrTerms) {
            if (obj instanceof Term) {
                terms++;
                if (!((Term) obj).isZero()) {
                    return false;
                }
            } else if (obj instanceof Operator) {
                return false;
            } else {
                throw new IllegalArgumentException("" + obj);
            }
        }
        return terms > 1 && terms < 5;
    }
}
