package net.sf.beezle.ssass.scss;

public class Pseudo implements BaseSelector {
    private final String function;
    private final String name;

    public Pseudo(String name) {
        this(null, name);
    }

    public Pseudo(String function, String name) {
        this.function = function;
        this.name = name;
    }

    @Override
    public void toCss(Output output) {
        output.string(":");
        if (function == null) {
            output.string(name);
        } else {
            output.string(function, "(", name, ")");
        }
    }
}
