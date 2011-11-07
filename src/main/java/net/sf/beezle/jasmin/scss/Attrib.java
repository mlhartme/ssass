package net.sf.beezle.jasmin.scss;

public class Attrib extends BaseSelector {
    private String name;
    private String value;

    public Attrib(String name, String value) {
        this.name = name;
        this.value = value;
    }
}
