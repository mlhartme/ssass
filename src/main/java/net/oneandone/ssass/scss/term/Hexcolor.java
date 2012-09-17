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

public class Hexcolor implements BaseTerm {
    private final String hexcolor;

    public Hexcolor(String hexcolor) {
        this.hexcolor = hexcolor;
    }

    @Override
    public void toCss(Output output) {
        output.string(output.compress()? compress(hexcolor) : hexcolor);
    }

    private static String compress(String hexcolor) {
        StringBuilder result;

        if (hexcolor.length() != 7) {
            return hexcolor;
        }
        for (int i = 0; i < 3; i++) {
            if (hexcolor.charAt(1 + i * 2) != hexcolor.charAt(1 + i * 2 + 1)) {
                return hexcolor;
            }
        }
        result = new StringBuilder(4);
        result.append(hexcolor.charAt(0));
        for (int i = 0; i < 3; i++) {
            result.append(hexcolor.charAt(1 + i * 2));
        }
        return result.toString();
    }
}
