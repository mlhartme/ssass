package net.sf.beezle.ssass.scss;

public class Stylesheet {
    private final String charset;
    private final Import[] imports;
    private final Statement[] statements;

    public Stylesheet(String charset, Import[] imports, Statement[] statements) {
        this.charset = charset;
        this.imports = imports;
        this.statements = statements;
    }

    public String toCss() {
        return "css style sheet"; // TODO
    }
}
