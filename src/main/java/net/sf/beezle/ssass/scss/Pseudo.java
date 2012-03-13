package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Pseudo implements BaseSelector {
    public static final String ELEMENT = "::";
    public static final String CLASS = ":";

    private final String type;
    private final String function;
    private final Object nameOrExpression;

    public Pseudo(String type, String name) {
        this(type, null, name);
    }

    public Pseudo(String type, String function, Object nameOrExpression) {
        this.type = type;
        this.function = function;
        this.nameOrExpression = nameOrExpression;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.string(type);
        if (function == null) {
            output.object(nameOrExpression);
        } else {
            output.object(function, nameOrExpression, ")");
        }
    }
}
