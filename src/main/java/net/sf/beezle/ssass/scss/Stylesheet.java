package net.sf.beezle.ssass.scss;

public class Stylesheet extends Base {
    private final String charset;
    private final Import[] imports;
    private final Statement[] statements;

    public Stylesheet(String charset, Import[] imports, Statement[] statements) {
        this.charset = charset;
        this.imports = imports;
        this.statements = statements;
    }

    @Override
    public void toCss(Output output) {
        if (charset != null) {
            output.string("@charset ", charset, ";\n");
        }
        output.base(imports);
        output.base(statements);
    }
}
