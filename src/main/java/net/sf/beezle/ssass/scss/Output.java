package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

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
        Output output;

        output = new Output(compress);
        s.toCss(output);
        return output.toString();
    }

    //--

    private final boolean compress;
    private final StringBuilder builder;
    private boolean first;
    private int indent;
    public final List<Selector[]> selectorContext;
    public final List<String> propertyContext;

    // TODO: block structure
    private final Map<String, Variable> variables;
    private final Map<String, Mixin> mixins;


    public void pushSelector(Selector[] context) {
        selectorContext.add(context);
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

    public Output(boolean compress) {
        this.compress = compress;
        this.builder = new StringBuilder();
        this.first = true;
        this.indent = 0;
        this.variables = new HashMap<String, Variable>();
        this.mixins = new HashMap<String, Mixin>();
        this.selectorContext = new ArrayList<Selector[]>();
        this.propertyContext = new ArrayList<String>();
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
            builder.append(str);
        }
    }

    public void open() {
        builder.append(compress ? "{" : " {\n");
        indent++;
        first = true;
    }

    public void close() {
        indent--;
        indent();
        builder.append(compress ? "}" : "}\n");
        first = true;
    }

    public void semicolon() {
        builder.append(compress ? ";" : ";\n");
        first = true;
    }

    public void semicolonOpt() {
        if (!compress) {
            builder.append("\n");
        } else {
            builder.append(';'); // yui always defines this. Nico doesn't know why, but the thinks that's not accidentially.
        }
        first = true;
    }

    public void spaceOpt() {
        if (!compress) {
            builder.append(' ');
        }
    }

    private void indent() {
        if (compress) {
            return;
        }
        for (int i = 0; i < indent; i++) {
            builder.append("  ");
        }
    }

    public Variable lookupVariable(String name) {
        return variables.get(name);
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

    public String propertyPrefix() {
        StringBuilder builder;

        builder = new StringBuilder();
        for (String property : propertyContext) {
            string(property, "-");
        }
        return builder.toString();
    }

    //--

    @Override
    public String toString() {
        return builder.toString();
    }
}
