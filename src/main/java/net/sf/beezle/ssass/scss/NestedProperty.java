package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class NestedProperty implements SsassDeclaration {
    private final String property;
    private final SsassDeclaration[] ssassDeclarations;

    public NestedProperty(String property, SsassDeclaration[] ssassDeclarations) {
        this.property = property;
        this.ssassDeclarations = ssassDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        output.pushProperty(property);
        first = true;
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (first) {
                first = false;
            } else {
                output.semicolon();
            }
            ssassDeclaration.toCss(output);
        }
        output.popProperty();
    }
}
