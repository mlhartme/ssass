package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Uri extends BaseTerm {
    private final String uri;

    public Uri(String uri) {
        this.uri = uri;
    }

    @Override
    public void toCss(Output output) {
        output.string(uri);
    }
}
