package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Namespace implements Base {
    /** may be null */
    private final String prefix;
    private final String uri;

    public Namespace(String prefix, String uri) {
        this.prefix = prefix;
        this.uri = uri;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.string("@namespace ");
        if (prefix != null) {
            output.string(prefix, " ");
        }
        output.string(uri);
        output.semicolon();
    }
}
