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
