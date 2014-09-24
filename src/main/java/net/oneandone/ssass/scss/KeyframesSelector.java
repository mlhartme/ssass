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

public class KeyframesSelector implements Base {
    private final String[] strings;

    public KeyframesSelector(String[] strings) {
        for (String string : strings) {
            switch (string) {
                case "from":
                case "to":
                    // ok
                    break;
                default:
                    if (!string.endsWith("%")) {
                        throw new IllegalArgumentException("invalid keyframe selector: " + string);
                    }
            }
        }
        this.strings = strings;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        first = true;
        for (String string : strings) {
            if (first) {
                first = false;
            } else {
                output.string(",");
            }
            output.string(string);
        }
    }
}
