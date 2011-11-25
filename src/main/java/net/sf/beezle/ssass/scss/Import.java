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

        output.write("@import ");
        output.write(src);
        first = true;
        for (String medium : mediaList) {
            if (first) {
                output.write(" ");
                first = false;
            } else {
                output.write(", ");
            }
            output.write(medium);
        }
        output.write(";\n");
    }
}
