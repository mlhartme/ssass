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
        output.write("@import ");
        output.write(src);
        for (String medium : mediaList) {
            output.write(" ");
            output.write(medium);
        }
        output.write(";\n");
    }
}
