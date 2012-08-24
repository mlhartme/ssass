/**
 * Copyright 1&1 Internet AG, http://www.1and1.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
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
