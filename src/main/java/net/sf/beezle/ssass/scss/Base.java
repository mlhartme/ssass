package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public interface Base {
    void toCss(Output output) throws GenericException;
}
