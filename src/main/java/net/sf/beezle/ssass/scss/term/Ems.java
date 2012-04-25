package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Ems implements BaseTerm {
    private String ems;

    public Ems(String ems) {
        this.ems = ems;
    }

    @Override
    public void toCss(Output output) {
        if (output.compress()) {
            if ("0em".equalsIgnoreCase(ems)) {
                output.string("0");
            } else {
                if (ems.startsWith("0.")) {
                    output.string(ems.substring(1));
                } else {
                    output.string(ems);
                }
            }
        } else {
            output.string(ems);
        }
    }
}
