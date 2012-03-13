package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.Expr;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.sushi.util.Strings;

public class Function implements BaseTerm {
    public final String name;
    public final Expr expr;

    public Function(String nameOpen, Expr expr) {
        this.name = Strings.removeRight(nameOpen, "(");
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object(name, "(", expr, ")");
    }
}
