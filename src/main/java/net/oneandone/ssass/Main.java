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
package net.oneandone.ssass;

import net.oneandone.mork.mapping.ExceptionErrorHandler;
import net.oneandone.mork.mapping.Mapper;
import net.oneandone.mork.misc.GenericException;
import net.oneandone.ssass.scss.Output;
import net.oneandone.ssass.scss.Stylesheet;
import net.oneandone.sushi.cli.Cli;
import net.oneandone.sushi.cli.Command;
import net.oneandone.sushi.cli.Console;
import net.oneandone.sushi.cli.Option;
import net.oneandone.sushi.cli.Remaining;

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
        this.mapper = new Mapper("net.oneandone.ssass.Mapper", new ExceptionErrorHandler());
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
