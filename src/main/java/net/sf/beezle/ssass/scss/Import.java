package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Import implements Base {
    private final String src;
    private final MediaQuery[] mediaQueryList;

    public Import(String src, MediaQuery[] mediaQueryList) {
        this.src = src;
        this.mediaQueryList = mediaQueryList;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        output.string("@import ", src);
        first = true;
        for (MediaQuery query : mediaQueryList) {
            if (first) {
                output.string(" ");
                first = false;
            } else {
                output.string(",");
                output.spaceOpt();
            }
            query.toCss(output);
        }
        output.semicolon();
    }
}
