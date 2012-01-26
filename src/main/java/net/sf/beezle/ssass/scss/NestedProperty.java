package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class NestedProperty implements NestedDeclaration {
    private final String property;
    private final NestedDeclaration[] nestedDeclarations;

    public NestedProperty(String property, NestedDeclaration[] nestedDeclarations) {
        this.property = property;
        this.nestedDeclarations = nestedDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        output.pushProperty(property);
        first = true;
        for (NestedDeclaration nestedDeclaration : nestedDeclarations) {
            if (first) {
                first = false;
            } else {
                output.semicolon();
            }
            if (nestedDeclaration instanceof Declaration) {
                nestedDeclaration.toCss(output);
            }
        }
        output.semicolonOpt();
        output.popProperty();
    }
}
