package net.sf.beezle.ssass.scss;

public class Pseudo implements BaseSelector {
    public static final String ELEMENT = "::";
    public static final String CLASS = ":";

    private final String type;
    private final String function;
    private final String name;

    public Pseudo(String type, String name) {
        this(type, null, name);
    }

    public Pseudo(String type, String function, String name) {
        this.type = type;
        this.function = function;
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        output.string(type);
        if (function == null) {
            output.string(name);
        } else {
            output.string(function, name, ")");
        }
    }
}
