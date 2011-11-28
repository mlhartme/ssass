package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public abstract class Base {
    public abstract void toCss(Output output) throws GenericException;
}
