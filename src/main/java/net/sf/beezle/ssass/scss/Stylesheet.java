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
            output.write("@charset ");
            output.write(charset);
            output.write(";\n");
        }
        for (Import imp : imports) {
            imp.toCss(output);
        }
        for (Statement statement : statements) {
            statement.toCss(output);
        }
    }
}
