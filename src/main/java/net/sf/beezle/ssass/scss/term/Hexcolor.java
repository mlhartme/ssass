package net.sf.beezle.ssass.scss.term;

import net.sf.beezle.ssass.scss.Output;

public class Hexcolor implements BaseTerm {
    private final String hexcolor;

    public Hexcolor(String hexcolor) {
        this.hexcolor = hexcolor;
    }

    @Override
    public void toCss(Output output) {
        output.string(output.compress()? compress(hexcolor) : hexcolor);
    }

    private static String compress(String hexcolor) {
        StringBuilder result;

        if (hexcolor.length() != 7) {
            return hexcolor;
        }
        for (int i = 0; i < 3; i++) {
            if (hexcolor.charAt(1 + i * 2) != hexcolor.charAt(1 + i * 2 + 1)) {
                return hexcolor;
            }
        }
        result = new StringBuilder(4);
        result.append(hexcolor.charAt(0));
        for (int i = 0; i < 3; i++) {
            result.append(hexcolor.charAt(1 + i * 2));
        }
        return result.toString();
    }
}
