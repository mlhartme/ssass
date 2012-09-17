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
import net.oneandone.sushi.util.Strings;

public class Mixin implements Statement {
    private final String name;
    public final String[] variables;
    private final SsassDeclaration[] ssassDeclarations;

    public Mixin(String name, String[] variables, SsassDeclaration[] ssassDeclarations) {
        if (name == null) {
            if (variables.length != 1) {
                throw new IllegalStateException();
            }
            this.name = variables[0];
            this.variables = new String[0];
        } else {
            this.name = Strings.removeRight(name, "(");
            this.variables = variables;
        }
        this.ssassDeclarations = ssassDeclarations;
    }

    public String getName() {
        return name;
    }

    public SsassDeclaration[] getSsassDeclarations() {
        return ssassDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.defineMixin(this);
    }
}
