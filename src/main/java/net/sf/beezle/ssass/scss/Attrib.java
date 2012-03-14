package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Attrib implements BaseSelector {
    private final TypeSelector typeSelector;
    private final AttribOp op;
    private final String value;

    public Attrib(TypeSelector typeSelector, AttribOp op, String value) {
        this.typeSelector = typeSelector;
        this.op = op;
        this.value = value;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object("[", typeSelector, op.toString(), value, "]");
    }
}
