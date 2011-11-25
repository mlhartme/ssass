package net.sf.beezle.ssass.scss;

public class Media extends Statement {
    private final String[] mediaList;
    private final Ruleset[] rulesets;

    public Media(String[] mediaList, Ruleset[] rulesets) {
        this.mediaList = mediaList;
        this.rulesets = rulesets;
    }

    @Override
    public void toCss(Output output) {
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
        output.string(" {\n");
        output.base(rulesets);
        output.string("}\n");
    }
}
