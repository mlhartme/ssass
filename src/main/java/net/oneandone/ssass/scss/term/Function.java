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
package net.oneandone.ssass.scss.term;

import net.oneandone.mork.misc.GenericException;
import net.oneandone.ssass.scss.Expr;
import net.oneandone.ssass.scss.Output;
import net.oneandone.sushi.util.Strings;

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
