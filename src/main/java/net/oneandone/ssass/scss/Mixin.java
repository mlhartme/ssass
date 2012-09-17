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
