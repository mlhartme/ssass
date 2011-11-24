package net.sf.beezle.ssass.scss;

public class Import {
    private final String src;
    private final String[] mediaList;

    public Import(String src, String[] mediaList) {
        this.src = src;
        this.mediaList = mediaList;
    }
}
