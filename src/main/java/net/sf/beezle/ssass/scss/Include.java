package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.term.Function;
import net.sf.beezle.ssass.scss.term.Ident;

public class Include implements SsassDeclaration {
    private final String mixin;
    private final Expr expression;

    public Include(Object identOrFunction) {
        Function fn;

        if (identOrFunction instanceof Ident) {
            this.mixin = ((Ident) identOrFunction).name;
            this.expression = null;
        } else {
            fn = (Function) identOrFunction;
            this.mixin = fn.name;
            this.expression = fn.expr;
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Mixin def;

        def = output.lookupMixin(mixin);
        if (def == null) {
            throw new GenericException("undefined mixin: " + mixin);
        }
        output.pushMixin(def, expression);

        boolean first;

        first = true;
        for (SsassDeclaration ssassDeclaration : def.getSsassDeclarations()) {
            if (ssassDeclaration instanceof Ruleset) {
                output.delay((Ruleset) ssassDeclaration);
            } else {
                if (first) {
                    first = false;
                } else {
                    output.semicolon();
                }
                ssassDeclaration.toCss(output);
            }
        }
        output.popMixin();
    }
}
