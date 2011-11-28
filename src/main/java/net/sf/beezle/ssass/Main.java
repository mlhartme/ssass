package net.sf.beezle.ssass;

import net.sf.beezle.mork.mapping.Mapper;
import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;
import net.sf.beezle.sushi.util.Strings;

public class Main {
    public static void main(String[] args) throws GenericException {
        Mapper mapper;
        int ok;
        long tmp;
        Stylesheet s;
        boolean compress;

        if (args.length == 0) {
            System.out.println("usage: ssass [-compress] <filename>+");
        } else {
            if ("-compress".equals(args[0])) {
                compress = true;
                args = Strings.cdr(args);
            } else {
                compress = false;
            }
            mapper = load();
            tmp = System.currentTimeMillis();
            ok = 0;
            for (String name : args) {
                System.out.println(name + ":");
                s = parse(mapper, name);
                if (s != null) {
                    System.out.println(Output.toCss(s, compress));
                    ok++;
                }
            }
            System.out.println(ok + "/" + args.length + " parsed successfully.");
            System.out.println((System.currentTimeMillis() - tmp) + " ms");
            System.exit(args.length - ok);
        }
    }

    public static Stylesheet parse(Mapper mapper, String file) {
        Object[] results;

        results = mapper.run(file);
        return results == null ? null : (Stylesheet) results[0];
    }

    public static Mapper load() {
        Mapper mapper;
        long tmp;

        System.out.print("loading mapper ... ");
        tmp = System.currentTimeMillis();
        mapper = new Mapper("net.sf.beezle.ssass.Mapper");
        System.out.println("done (" + (System.currentTimeMillis() - tmp) + " ms)");
        return mapper;
    }
}
