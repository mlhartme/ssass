package net.sf.beezle.ssass.scss;

public class Media extends Statement {
    private final String[] mediaList;
    private final Ruleset[] rulesets;

    public Media(String[] mediaList, Ruleset[] rulesets) {
        this.mediaList = mediaList;
        this.rulesets = rulesets;
    }
}
