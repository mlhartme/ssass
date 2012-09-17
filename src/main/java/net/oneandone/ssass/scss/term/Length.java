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
