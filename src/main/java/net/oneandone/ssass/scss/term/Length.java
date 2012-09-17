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

public class Length implements BaseTerm {
    private final String len;

    public Length(String len) {
        this.len = len;
    }

    @Override
    public void toCss(Output output) {
        if (output.compress() && isZero()) {
            output.string("0");
        } else {
            output.string(len);
        }
    }

    public boolean isZero() {
        return len.length() == 3 && len.charAt(0) == '0';
    }
}
