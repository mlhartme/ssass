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
