package net.sf.beezle.ssass;

import de.mlhartme.mork.mapping.Mapper;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;
import net.sf.beezle.sushi.fs.World;
import net.sf.beezle.sushi.fs.file.FileNode;
import net.sf.beezle.sushi.util.Strings;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class SsassTest {
    private static final World world = new World();
    private static final Mapper mapper = Main.load();

    @Test
    public void empty() throws IOException {
        check();
    }

    @Test
    public void charset() throws IOException {
        check("@charset \"ISO-8859-1\";");
    }

    @Test
    public void imports() throws IOException {
        check(
                "@import url('/css/typography.css');",
                "@import url('/css/layout.css') print;",
                "@import url('/css/color.css') foo, bar;");
    }

    @Test
    public void ruleset() throws IOException {
        check("p {\n" +
              "margin-top: abc;\n" +
              "margin-right: xyz !important\n" +
              "}");
    }

    @Test
    public void todo() throws IOException {
        check();
    }

    private void check(String ... lines) throws IOException {
        FileNode tmp;
        Stylesheet s;
        Output output;

        tmp = (FileNode) world.getTemp().createTempFile().writeLines(lines);
        s = Main.parse(mapper, tmp.getAbsolute());
        tmp.delete();
        output = new Output();
        s.toCss(output);
        assertEquals(Strings.join("\n", lines), output.toString().trim());
    }
}
