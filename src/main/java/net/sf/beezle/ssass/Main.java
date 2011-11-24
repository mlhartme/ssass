package net.sf.beezle.ssass;

import de.mlhartme.mork.mapping.Mapper;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;

public class Main {
    public static void main(String[] args) {
        Mapper mapper;
        int ok;
        long tmp;
        Object[] results;

        if (args.length == 0) {
            System.out.println("usage: ssass <filename>+");
        } else {
            mapper = load();
            tmp = System.currentTimeMillis();
            ok = 0;
            for (String name : args) {
                System.out.println(name + ":");
                results = mapper.run(name);
                if (results != null) {
                    Stylesheet s = (Stylesheet) results[0];
                    s.toCss(new Output());
                    ok++;
                }
            }
            System.out.println(ok + "/" + args.length + " parsed successfully.");
            System.out.println((System.currentTimeMillis() - tmp) + " ms");
            System.exit(args.length - ok);
        }
    }

    private static Mapper load() {
        Mapper mapper;
        long tmp;

        System.out.print("loading mapper ... ");
        tmp = System.currentTimeMillis();
        mapper = new Mapper("net.sf.beezle.ssass.Mapper");
        System.out.println("done (" + (System.currentTimeMillis() - tmp) + " ms)");
        return mapper;
    }
}
