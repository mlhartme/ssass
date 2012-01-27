package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Mixin implements Statement {
    private final String name;
    private final NestedDeclaration[] nestedDeclarations;

    public Mixin(String name, NestedDeclaration[] nestedDeclarations) {
        this.name = name;
        this.nestedDeclarations = nestedDeclarations;
    }

    public String getName() {
        return name;
    }

    public NestedDeclaration[] getNestedDeclarations() {
        return nestedDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.defineMixin(this);
    }
}
