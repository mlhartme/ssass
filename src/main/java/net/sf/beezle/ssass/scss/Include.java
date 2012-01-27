package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Include implements SsassDeclaration {
    private final String mixin;

    public Include(String mixin) {
        this.mixin = mixin;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Mixin def;

        def = output.lookupMixin(mixin);
        if (def == null) {
            throw new GenericException("undefined mixin: " + mixin);
        }
        for (SsassDeclaration decl : def.getSsassDeclarations()) {
            decl.toCss(output);
        }
    }
}
