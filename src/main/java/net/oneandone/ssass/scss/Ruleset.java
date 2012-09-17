/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
