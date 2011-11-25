package net.sf.beezle.ssass.scss;

public class Output {
    public static String compress(Stylesheet s) {
        return toCss(s, true);
    }
    public static String prettyprint(Stylesheet s) {
        return toCss(s, false);
    }

    public static String toCss(Stylesheet s, boolean compress) {
        Output output;

        output = new Output(compress);
        s.toCss(output);
        return output.toString();
    }

    //--

    private final boolean compress;
    private final StringBuilder builder;
    private boolean first;
    private int indent;

    public Output(boolean compress) {
        this.compress = compress;
        this.builder = new StringBuilder();
        this.first = true;
        this.indent = 0;
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
        builder.append(compress ? "{" : " {\n");
        indent++;
        first = true;
    }

    public void close() {
        indent--;
        indent();
        builder.append(compress ? "}" : "}\n");
        first = true;
    }

    public void semicolon() {
        builder.append(compress ? ";" : ";\n");
        first = true;
    }

    public void semicolonOpt() {
        if (!compress) {
            builder.append("\n");
        } else {
            builder.append(';'); // TODO: why?
        }
        first = true;
    }

    public void spaceOpt() {
        if (!compress) {
            builder.append(' ');
        }
    }

    private void indent() {
        if (compress) {
            return;
        }
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
