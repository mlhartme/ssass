package net.sf.beezle.ssass.scss;

import net.sf.beezle.mork.misc.GenericException;

public class SimpleSelectorSequence implements Base {
    private BaseSelector[] bases;

    public SimpleSelectorSequence(ElementName first, BaseSelector[] bases) {
        if (first == null) {
            this.bases = bases;
        } else {
            this.bases = new BaseSelector[bases.length + 1];
            this.bases[0] = first;
            System.arraycopy(bases, 0, this.bases, 1, bases.length);
        }
    }

    @Override
    public void toCss(Output output) throws GenericException {
        output.base(bases);
    }
}
