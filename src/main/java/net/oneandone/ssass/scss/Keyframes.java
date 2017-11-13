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

public class Keyframes implements Statement {
    private final String identifier;
    private final KeyframesBlock[] blocks;

    public Keyframes(String identifier, KeyframesBlock[] blocks) {
        this.identifier = identifier;
        this.blocks = blocks;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.string("@keyframes ");
        output.string(identifier);
        output.open();
        for (KeyframesBlock block : blocks) {
            block.toCss(output);
        }
        output.close();
    }
}
