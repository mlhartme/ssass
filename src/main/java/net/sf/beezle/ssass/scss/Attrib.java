package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Attrib implements BaseSelector {
    private String name;
    private AttribOp op;
    private String value;

    public Attrib(String name, AttribOp op, String value) {
        this.name = name;
        this.op = op;
        this.value = value;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.object("[", name, op.toString(), value, "]");
    }
}
