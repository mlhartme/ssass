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

public class SimpleSelectorSequence implements Base {
    private BaseSelector[] bases;

    public SimpleSelectorSequence(TypeSelector first, BaseSelector[] bases) {
        if (first == null) {
            this.bases = bases;
        } else {
            this.bases = new BaseSelector[bases.length + 1];
            this.bases[0] = first;
            System.arraycopy(bases, 0, this.bases, 1, bases.length);
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.base(bases);
    }
}
