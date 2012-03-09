package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Media implements Statement {
    private final MediaQuery[] mediaQueryList;
    private final Ruleset[] rulesets;

    public Media(MediaQuery[] mediaQueryList, Ruleset[] rulesets) {
        this.mediaQueryList = mediaQueryList;
        this.rulesets = rulesets;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        output.string("@media");
        first = true;
        for (MediaQuery query : mediaQueryList) {
            if (first) {
                output.string(" ");
                first = false;
            } else {
                output.string(", ");
            }
            query.toCss(output);
        }
        output.open();
        output.base(rulesets);
        output.close();
    }
}
