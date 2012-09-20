/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.oneandone.ssass.scss;

import net.oneandone.mork.misc.GenericException;
import net.oneandone.ssass.scss.term.Function;
import net.oneandone.ssass.scss.term.Ident;

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

        def = output.lookupMixin(mixin, expression);
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
