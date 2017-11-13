/*
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

public class KeyframesBlock implements Base {
    private final KeyframesSelector selector;
    private final SsassDeclaration[] declarations;

    public KeyframesBlock(KeyframesSelector selector, SsassDeclaration[] declarations) {
        this.selector = selector;
        this.declarations = declarations;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        selector.toCss(output);
        Ruleset.toCss(declarations, output);
    }
}