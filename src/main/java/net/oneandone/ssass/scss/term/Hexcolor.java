/**
 * Copyright 1&1 Internet AG, http://www.1and1.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.oneandone.ssass.scss.term;

import net.oneandone.ssass.scss.Output;

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
