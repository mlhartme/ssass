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
