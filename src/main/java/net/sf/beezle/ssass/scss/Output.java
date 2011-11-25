package net.sf.beezle.ssass.scss;

public class Output {
    private final StringBuilder builder;
    private boolean first;
    private int indent;

    public Output() {
        builder = new StringBuilder();
        first = true;
        indent = 0;
    }

    public void object(Object ... objs) {
        for (Object obj : objs) {
            if (obj instanceof String) {
                string((String) obj);
            } else if (obj instanceof Base) {
                base((Base) obj);
            } else if (obj == null) {
                //
            } else {
                throw new IllegalArgumentException(obj.getClass().toString());
            }
        }
    }

    public void base(Base ... bases) {
        for (Base base : bases) {
            base.toCss(this);
        }
    }

    //--

    public void string(String ... strs) {
        for (String str : strs) {
            if (first) {
                indent();
                first = false;
            }
            builder.append(str);
        }
    }

    public void open() {
        builder.append(" {\n");
        indent++;
        first = true;
    }

    public void close() {
        indent--;
        indent();
        builder.append("}\n");
        first = true;
    }

    public void semicolon() {
        builder.append(";\n");
        first = true;
    }

    public void semicolonOpt() {
        builder.append("\n");
        first = true;
    }

    private void indent() {
        for (int i = 0; i < indent; i++) {
            builder.append("  ");
        }
    }

    //--

    @Override
    public String toString() {
        return builder.toString();
    }
}
