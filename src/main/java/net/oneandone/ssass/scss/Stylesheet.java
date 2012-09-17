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
