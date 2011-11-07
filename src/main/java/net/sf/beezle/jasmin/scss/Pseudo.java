package net.sf.beezle.jasmin.scss;

import net.sf.beezle.jasmin.scss.term.Strng;

public class Pseudo extends BaseSelector {
    private final String function;
    private final String name;

    public Pseudo(String name) {
        this(null, name);
    }

    public Pseudo(String function, String name) {
        this.function = function;
        this.name = name;
    }
}
