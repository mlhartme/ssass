package net.sf.beezle.ssass.scss;

public class Stylesheet extends Base {
    private final String charset;
    private final Import[] imports;
    private final Variables variables;
    private final Statement[] statements;

    public Stylesheet(String charset, Import[] imports, Variables variables, Statement[] statements) {
        this.charset = charset;
        this.imports = imports;
        this.variables = variables;
        this.statements = statements;
    }

    @Override
    public void toCss(Output output) {
        if (charset != null) {
            output.string("@charset ", charset);
            output.semicolon();
        }
        output.base(imports);
        output.base(variables);
        output.base(statements);
    }
}
