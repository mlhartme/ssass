package net.sf.beezle.ssass.scss;

public class AttribOp {
    public static final AttribOp EQ = new AttribOp("=");
    public static final AttribOp INCLUDES = new AttribOp("~=");
    public static final AttribOp DASHMATCH = new AttribOp("|=");
    private final String op;

    private AttribOp(String op) {
        this.op = op;
    }

    public String toString() {
        return op;
    }
}
