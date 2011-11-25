package net.sf.beezle.ssass.scss;

public class Import extends Base {
    private final String src;
    private final String[] mediaList;

    public Import(String src, String[] mediaList) {
        this.src = src;
        this.mediaList = mediaList;
    }

    @Override
    public void toCss(Output output) {
        boolean first;

        output.string("@import ", src);
        first = true;
        for (String medium : mediaList) {
            if (first) {
                output.string(" ");
                first = false;
            } else {
                output.string(", ");
            }
            output.string(medium);
        }
        output.string(";\n");
    }
}
