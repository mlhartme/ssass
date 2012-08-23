package net.sf.beezle.ssass;

import net.sf.beezle.mork.mapping.Mapper;
import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;
import net.sf.beezle.sushi.cli.Cli;
import net.sf.beezle.sushi.cli.Command;
import net.sf.beezle.sushi.cli.Console;
import net.sf.beezle.sushi.cli.Option;
import net.sf.beezle.sushi.cli.Remaining;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Cli implements Command {
    public static void main(String[] args) {
        Main main;

        main = new Main();
        System.exit(main.run(args));
    }

    @Option("compress")
    private boolean compress;

    private List<String> files;
    private final Mapper mapper;

    public Main() {
        this.files = new ArrayList<>();
        this.mapper = new Mapper("net.sf.beezle.ssass.Mapper");
    }

    public Console getConsole() {
        return console;
    }

    @Remaining
    public void add(String file) {
        files.add(file);
    }

    public void invoke() throws GenericException, IOException {
        int ok;
        long tmp;
        Stylesheet s;

        if (files.size() == 0) {
            printHelp();
            return;
        }
        tmp = System.currentTimeMillis();
        ok = 0;
        for (String file : files) {
            console.info.println(file + ":");
            s = parse(file);
            if (s != null) {
                System.out.println(Output.toCss(s, compress));
                ok++;
            }
        }
        console.info.println(ok + "/" + files.size() + " parsed successfully.");
        console.info.println((System.currentTimeMillis() - tmp) + " ms");
        System.exit(files.size() - ok);
    }

    public Stylesheet parse(String file) throws IOException {
        Object[] results;

        if (console.getVerbose()) {
            mapper.setLogging(console.verbose, console.verbose);
        }
        results = mapper.run(file);
        return results == null ? null : (Stylesheet) results[0];
    }

    public void printHelp() {
        console.info.println("usage: ssass [-v] [-compress] <filename>+");
    }
}
