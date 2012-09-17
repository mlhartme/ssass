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

public class Pseudo implements BaseSelector {
    public static final String ELEMENT = "::";
    public static final String CLASS = ":";

    private final String type;
    private final String function;
    private final Object nameOrExpression;

    public Pseudo(String type, String name) {
        this(type, null, name);
    }

    public Pseudo(String type, String function, Object nameOrExpression) {
        this.type = type;
        this.function = function;
        this.nameOrExpression = nameOrExpression;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.string(type);
        if (function == null) {
            output.object(nameOrExpression);
        } else {
            output.object(function, nameOrExpression, ")");
        }
    }
}
