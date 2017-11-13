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
import net.oneandone.ssass.scss.term.BaseTerm;

public class Reference implements BaseTerm {
    private final String name;

    public Reference(String name) throws GenericException {
        this.name = name;
    }

    @Override
    public void toCss(Output output) throws GenericException {
        Expr value;

        value = output.lookupVariable(name);
        if (value == null) {
            throw new GenericException("undefined variable: " + name);
        }
        value.toCss(output);
    }
    // kaputt: http://localhost:8080/xml/jasmin/get/150120-1608/frontend-skin-catweasel-components-forms+frontend-performance-tool-pages-styleguide-widgets+!qx-log+!stageassistent-components-stageassistent+!qx-base+!qx-matchmedia+!qx-xhr+!frontend-skin-common+!frontend-panel-content-components-header+!frontend-skin-catweasel-components-buttons+!frontend-skin-catweasel-components-tooltips+!frontend-skin-catweasel-components-quotas+!frontend-skin-catweasel-components-container+!frontend-skin-catweasel-components-content+!frontend-skin-catweasel-components-decorations+!frontend-skin-catweasel-components-footer+!frontend-skin-catweasel-components-grid+!frontend-skin-catweasel-components-header+!frontend-skin-catweasel-components-typography+!frontend-skin-catweasel-components-info-box+!frontend-performance-tool-components-container+!frontend-performance-tool-components-content+!frontend-performance-tool-components-footer+!frontend-performance-tool-components-header+suffix/css/lead
    // kaputt: http://localhost:8080/xml/jasmin/get/150120-1608/frontend-skin-catweasel-components-forms+!frontend-skin-catweasel-components-info-box/css/lead
}
