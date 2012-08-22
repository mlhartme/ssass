package net.sf.beezle.ssass.scss;

public class Hash implements BaseSelector {
    private final String hash;

    public Hash(String hash) {
        this.hash = hash;
    }

    @Override
    public void toCss(Output output) {
        output.string(hash);
    }
}