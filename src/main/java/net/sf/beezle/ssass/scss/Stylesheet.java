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
