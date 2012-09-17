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
package net.oneandone.ssass.scss;

import net.oneandone.mork.misc.GenericException;

public class Ruleset implements Statement, SsassDeclaration {
    private Selector[] selectors;
    private SsassDeclaration[] ssassDeclarations;

    public Ruleset(Selector[] selectors, SsassDeclaration[] ssassDeclarations) {
        this.selectors = selectors;
        this.ssassDeclarations = ssassDeclarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        if (output.compress() && isEmpty(ssassDeclarations)) {
            return;
        }
        output.selectors(selectors);
        toCss(selectors, ssassDeclarations, output);
    }

    private static final Selector[] NO_SELECTORS = new Selector[0];

    public static void toCss(SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        toCss(NO_SELECTORS, ssassDeclarations, output);
    }

    private static boolean isEmpty(SsassDeclaration[] ssassDeclarations) {
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (ssassDeclaration instanceof Ruleset) {
                // nothing
            } else {
                return false;
            }
        }
        return true;
    }

    public static void toCss(Selector[] context, SsassDeclaration[] ssassDeclarations, Output output) throws GenericException {
        boolean first;

        output.pushSelector(context);
        output.open();
        first = true;
        for (SsassDeclaration ssassDeclaration : ssassDeclarations) {
            if (ssassDeclaration instanceof Ruleset) {
                output.delay((Ruleset) ssassDeclaration);
            } else {
                if (first) {
                    first = false;
                } else {
                    output.semicolon();
                }
                ssassDeclaration.toCss(output);
            }
        }
        if (!first) {
            output.semicolonOpt();
        }
        output.close();
        output.popSelector();
        if (output.isTopLevel()) {
            output.delayed();
        }
    }
}
