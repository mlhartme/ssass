package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Mixin implements Statement {
    private final String name;
    private final SsassDeclaration[] ssassDeclarations;

    public Mixin(String name, SsassDeclaration[] ssassDeclarations) {
        this.name = name;
        this.ssassDeclarations = ssassDeclarations;
    }

    public String getName() {
        return name;
    }

    public SsassDeclaration[] getSsassDeclarations() {
        return ssassDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.defineMixin(this);
    }
}
