package net.sf.beezle.ssass.scss;

public class Output {
    private final StringBuilder builder;

    public Output() {
        builder = new StringBuilder();
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


    public void string(String ... strs) {
        for (String str : strs) {
            builder.append(str);
        }
    }
    public void base(Base ... bases) {
        for (Base base : bases) {
            base.toCss(this);
        }
    }

    public void open() {
        builder.append(" {\n");
    }
    public void close() {
        builder.append("}\n");
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
