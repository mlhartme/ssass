/**
 * Copyright 1&1 Internet AG, https://github.com/1and1/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
