/*
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
import net.oneandone.ssass.scss.term.Ident;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/** To processe isolates sass files - implicitly defines otherwise undefined variables and mixins */
public class AutoDefineOutput extends Output {
    public AutoDefineOutput(Writer dest, boolean compress) {
        super(dest, compress);
    }

    public Expr lookupVariable(String name) {
        Expr result;

        result = super.lookupVariable(name);
        if (result == null) {
            result = new Expr(new Object[] { new Term(new Ident("undefined")) } );
            try {
                defineVariable(new Variable(name, result));
            } catch (GenericException e) {
                throw new IllegalStateException(e);
            }
        }
        return result;
    }

    public Mixin lookupMixin(String name, Expr arguments) {
        Mixin mixin;
        String[] variables;

        mixin = super.lookupMixin(name, arguments);
        if (mixin == null) {
            variables = variables(arguments);
            if (variables.length == 0) {
                mixin = new Mixin(null, new String[] { name  }, new SsassDeclaration[0]);
            } else {
                mixin = new Mixin(name + "(", variables, new SsassDeclaration[0]);
            }
        }
        return mixin;
    }

    private String[] variables(Expr argument) {
        int count;
        String[] result;

        if (argument == null) {
            count = 0;
        } else {
            List<Expr> lst = new ArrayList<>();
            try {
                argument.toArguments(lst);
            } catch (GenericException e) {
                throw new IllegalStateException();
            }
            count = lst.size();
        }
        result = new String[count];
        for (int i = 0; i < count; i++) {
            result[i] = "arg" + i;
        }
        return result;
    }
}
