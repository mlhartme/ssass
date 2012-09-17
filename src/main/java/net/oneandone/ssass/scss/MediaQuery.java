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

public class MediaQuery implements Base {
    /** may be null */
    private final MediaOperator op;

    /** may be null */
    private final String type;

    private final MediaExpr[] exprs;

    public MediaQuery(MediaOperator op, String type, MediaExpr[] exprs) {
        this.op = op;
        this.type = type;
        this.exprs = exprs;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        boolean first;

        if (op != null) {
            output.string(op.toString());
            output.string(" ");
        }
        if (type != null) {
            output.string(type);
            first = false;
        } else {
            first = true;
        }
        for (MediaExpr expr : exprs) {
            if (first) {
                first = false;
            } else {
                output.string(" and ");
            }
            expr.toCss(output);
        }
    }
}
