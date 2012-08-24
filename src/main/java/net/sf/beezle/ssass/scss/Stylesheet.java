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

public class Stylesheet implements Base {
    private final String charset;
    private final Import[] imports;
    private final Namespace[] namespaces;
    private final Statement[] statements;

    public Stylesheet(String charset, Import[] imports, Namespace[] namespaces, Statement[] statements) {
        this.charset = charset;
        this.imports = imports;
        this.namespaces = namespaces;
        this.statements = statements;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        if (charset != null) {
            output.string("@charset ", charset);
            output.semicolon();
        }
        output.base(imports);
        output.base(namespaces);
        output.base(statements);
    }
}
