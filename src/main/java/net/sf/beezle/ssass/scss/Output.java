/**
 * Copyright 1&1 Internet AG, http://www.1and1.org
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Output {
    public static String compress(Stylesheet s) throws GenericException {
        return toCss(s, true);
    }
    public static String prettyprint(Stylesheet s) throws GenericException {
        return toCss(s, false);
    }

    public static String toCss(Stylesheet s, boolean compress) throws GenericException {
        StringWriter sw;
        Output output;

        sw = new StringWriter();
        output = new Output(sw, compress);
        s.toCss(output);
        try {
            output.result().close();
        } catch (IOException e) {
            throw new IllegalStateException("unexpected io exception while writing to StringWriter", e);
        }
        return sw.toString();
    }

    //--

    private final Writer dest;
    private boolean compress;
    private boolean first;
    private int indent;
    private final List<Selector[]> selectorContext;
    private final List<String> propertyContext;
    private final List<Selector[]> delayedContexts;
    private final List<Ruleset> delayedRulesets;

    private final Map<String, Mixin> mixins;

    private final Map<String, Variable> variables;
    private final List<Map<String, Expr>> mixinContexts;

    private final List<IOException> exceptions;

    public Output(Writer dest, boolean compress) {
        this.dest = dest;
        this.compress = compress;
        this.first = true;
        this.indent = 0;
        this.variables = new HashMap<>();
        this.mixins = new HashMap<>();
        this.selectorContext = new ArrayList<>();
        this.propertyContext = new ArrayList<>();
        this.delayedContexts = new ArrayList<>();
        this.delayedRulesets = new ArrayList<>();
        this.mixinContexts = new ArrayList<>();
        this.exceptions = new ArrayList<>();
    }

    public Writer result() throws IOException {
        switch (exceptions.size()) {
            case 0:
                return dest;
            case 1:
                throw exceptions.get(0);
            default:
                throw new IOException(exceptions.size() + " IOExceptions, first is: " + exceptions.get(0).getMessage());
        }
    }

    public boolean compress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public void pushSelector(Selector[] context) {
        int size;

        size = selectorContext.size();
        if (size == 0) {
            selectorContext.add(context);
        } else {
            selectorContext.add(Selector.cross(selectorContext.get(size - 1), context));
        }
    }

    public void popSelector() {
        selectorContext.remove(selectorContext.size() - 1);
    }

    public void pushProperty(String property) {
        propertyContext.add(property);
    }
    public void popProperty() {
        propertyContext.remove(propertyContext.size() - 1);
    }

    public void object(Object ... objs) throws GenericException {
        for (Object obj : objs) {
            if (obj instanceof String) {
                string((String) obj);
            } else if (obj instanceof Base) {
                base((Base) obj);
            } else if (obj == null) {
                //
            } else {
                throw new IllegalArgumentException(obj.getClass().toString());
            }
        }
    }

    public void selectors(Selector[] selectors) throws GenericException {
        int size;
        Selector[] lst;
        boolean first;

        size = selectorContext.size();
        if (size == 0) {
            lst = selectors;
        } else {
            lst = Selector.cross(selectorContext.get(size - 1), selectors);
        }
        first = true;
        for (Selector selector : lst) {
            if (first) {
                first = false;
            } else {
                string(",");
                spaceOpt();
            }
            selector.toCss(this);
        }
    }

    public void base(Base ... bases) throws GenericException {
        for (Base base : bases) {
            base.toCss(this);
        }
    }

    //--

    public void string(String ... strs) {
        for (String str : strs) {
            if (first) {
                indent();
                first = false;
            }
            write(str);
        }
    }

    public void open() {
        write(compress ? "{" : " {\n");
        indent++;
        first = true;
    }

    public void close() {
        indent--;
        indent();
        write(compress ? "}" : "}\n");
        first = true;
    }

    public void semicolon() {
        write(compress ? ";" : ";\n");
        first = true;
    }

    public void semicolonOpt() {
        if (!compress) {
            write("\n");
        } else {
            write(';'); // yui always defines this. Nico doesn't know why, but the thinks that's not accidentially.
        }
        first = true;
    }

    public void spaceOpt() {
        if (!compress) {
            write(' ');
        }
    }

    private void indent() {
        if (compress) {
            return;
        }
        for (int i = 0; i < indent; i++) {
            write("  ");
        }
    }

    public Expr lookupVariable(String name) {
        Map<String, Expr> context;
        Variable variable;
        Expr result;

        for (int i = mixinContexts.size() - 1; i >= 0; i--) {
            context = mixinContexts.get(i);
            result = context.get(name);
            if (result != null) {
                return result;
            }
        }
        variable = variables.get(name);
        return variable == null ? null : variable.getExpr();
    }

    public void defineVariable(Variable var) throws GenericException {
        if (variables.put(var.getName(), var) != null) {
            throw new GenericException("duplicate variable");
        }
    }

    public Mixin lookupMixin(String name) {
        return mixins.get(name);
    }

    public void defineMixin(Mixin mixin) throws GenericException {
        if (mixins.put(mixin.getName(), mixin) != null) {
            throw new GenericException("duplicate mixin");
        }
    }

    public void pushMixin(Mixin mixin, Expr expr) throws GenericException {
        Map<String, Expr> data;
        List<Expr> arguments;

        arguments = new ArrayList<Expr>();
        if (expr != null) {
            expr.toArguments(arguments);
        }
        if (mixin.variables.length != arguments.size()) {
            throw new GenericException("argument count mismatch: " + mixin.variables.length + " vs " + arguments.size());
        }
        data = new HashMap<String, Expr>();
        for (int i = 0; i < arguments.size(); i++) {
            data.put(mixin.variables[i], arguments.get(i));
        }
        mixinContexts.add(data);
    }

    public void popMixin() {
        mixins.remove(mixinContexts.size() - 1);
    }

    public String propertyPrefix() {
        StringBuilder builder;

        builder = new StringBuilder();
        for (String property : propertyContext) {
            string(property, "-");
        }
        return builder.toString();
    }

    public boolean isTopLevel() {
        return selectorContext.size() == 0;
    }

    public void delay(Ruleset ruleset) {
        delayedContexts.add(selectorContext.get(selectorContext.size() - 1));
        delayedRulesets.add(ruleset);
    }

    public void delayed() throws GenericException {
        if (!selectorContext.isEmpty()) {
            // because we're executing top-level
            throw new IllegalStateException();
        }

        // size grows!
        for (int i = 0; i < delayedContexts.size(); i++) {
            selectorContext.clear();
            selectorContext.add(delayedContexts.get(i));
            delayedRulesets.get(i).toCss(this);
        }
        delayedContexts.clear();
        delayedRulesets.clear();
    }

    //--

    private void write(String str) {
        try {
            dest.write(str);
        } catch (IOException e) {
            exceptions.add(e);
        }
    }

    private void write(char c) {
        try {
            dest.write(c);
        } catch (IOException e) {
            exceptions.add(e);
        }
    }

}
