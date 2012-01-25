package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class Media implements Statement {
    private final String[] mediaList;
    private final Ruleset[] rulesets;

    public Media(String[] mediaList, Ruleset[] rulesets) {
        this.mediaList = mediaList;
        this.rulesets = rulesets;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        output.string("@media");
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
        output.open();
        output.base(rulesets);
        output.close();
    }
}
