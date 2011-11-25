package net.sf.beezle.ssass.scss;

public class Attrib extends BaseSelector {
    private String name;
    private AttribOp op;
    private String value;

    public Attrib(String name, AttribOp op, String value) {
        this.name = name;
        this.op = op;
        this.value = value;
    }

    @Override
    public void toCss(Output output) {
        output.object("[", name, op.toString(), value, "]");
    }
}
