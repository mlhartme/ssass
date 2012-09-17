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

public class TypeSelector implements BaseSelector {
    public static final String NAMESPACE_STAR = "*";
    public static final String NAMESPACE_EMPTY = "";

    public static TypeSelector universal(String namespace) {
        return new TypeSelector(namespace, "*");
    }

    private final String namespace;
    private final String elementName;

    public TypeSelector(String one, String two) {
        if (two == null) {
            this.namespace = null;
            this.elementName = one;
        } else {
            this.namespace = one;
            this.elementName = two;
        }
    }

    @Override
    public void toCss(Output output) {
        if (namespace != null) {
            output.string(namespace);
            output.string("|");
        }
        output.string(elementName);
    }
}
