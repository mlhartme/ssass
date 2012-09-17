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

public class Variable implements Statement {
    private final String name;
    private final Expr expr;

    public Variable(String name, Expr expr) {
        this.name = name;
        this.expr = expr;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.defineVariable(this);
    }

    public String getName() {
        return name;
    }

    public Expr getExpr() {
        return expr;
    }
}
