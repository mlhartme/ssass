package net.sf.beezle.jasmin;

import de.mlhartme.mork.mapping.Mapper;

public class Main {
    public static void main(String[] args) {
        Mapper mapper;
        int ok;
        long tmp;

        if (args.length == 0) {
            System.out.println("usage: scss <filename>+");
        } else {
            mapper = load();
            tmp = System.currentTimeMillis();
            ok = 0;
            for (String name : args) {
                System.out.println(name + ":");
                if (mapper.run(name) != null) {
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
        mapper = new Mapper("jasmin.Mapper");
        System.out.println("done (" + (System.currentTimeMillis() - tmp) + " ms)");
        return mapper;
    }
}
