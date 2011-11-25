package net.sf.beezle.ssass;

import com.yahoo.platform.yui.compressor.CssCompressor;
import net.sf.beezle.mork.mapping.Mapper;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;
import net.sf.beezle.sushi.fs.World;
import net.sf.beezle.sushi.fs.file.FileNode;
import net.sf.beezle.sushi.io.OS;
import net.sf.beezle.sushi.util.Separator;
import net.sf.beezle.sushi.util.Strings;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

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
        check("p {",
              "  margin-top: abc;",
              "  margin-right: xyz !important",
              "}");
    }

    @Test
    public void media() throws IOException {
        check(
                "@media print {",
                "  p {",
                "    font-family: Arial,Helvetica,sans-serif;",
                "    font-size: 11px;",
                "    font-weight: bold",
                "  }",
                "}",
                "@media screen {",
                "  p {",
                "    font-family: Arial,Helvetica,sans-serif;",
                "    font-size: 11px;",
                "    font-weight: bold",
                "  }",
                "  p.print {",
                "    display: none",
                "  }",
                "}");
    }

    @Test
    public void page() throws IOException {
        check(
                "@page {",
                "  size: 21.0cm 14.85cm;",
                "  margin-top: 1.5cm;",
                "  margin-bottom: 2cm",
                "}",
                "@page :left {",
                "  margin-left: 1.5cm;",
                "  margin-right: 2cm",
                "}",
                "@page :right {",
                "  margin-left: 2cm;",
                "  margin-right: 1.5cm",
                "}");
    }

    @Test
    public void pseudoFunctionSelector() throws IOException {
        check(
                "tr:nth-child(odd) td {",
                "  background: #C1B49A",
                "}");
    }

    @Test
    public void function() throws IOException {
        check(
                "p:before {",
                "  counter-increment: paras 1;",
                "  content: \"New Paragraph:\" counter(paras,decimal) \":\"",
                "}");
    }

    @Test
    public void attrib() throws IOException {
        check(
                "foo[bar=\"x\"] {",
                "  background-color: yellow",
                "}",
                "a[title~=Web] {",
                "  background-color: yellow",
                "}",
                "[lang|=fr] {",
                "  background-color: grey",
                "}");
    }

    @Test
    public void complex() throws IOException {
        FileNode src;
        Stylesheet s;

        src = world.guessProjectHome(getClass()).join("src/test/complex.css");
        s = Main.parse(mapper, src.getAbsolute());
        assertEquals(src.readString(), Output.prettyprint(s));
        assertEquals(compress(src.readString()), Output.compress(s));
    }

    //-- compression issues

    @Ignore // TODO
    public void weiredFunctionCompression() throws IOException {
        check(
                "p:before {",
                "  counter-increment: paras 1;",
                "  content: \"New Paragraph: \" counter(paras,decimal) \": \"",
                "}");
    }

    @Ignore // TODO
    public void hexColorCompress() throws IOException {
        check(
                "foo[bar=\"x\"] {",
                "  background-color: #ffffff",
                "}");
    }

    //--

    private void check(String ... lines) throws IOException {
        String orig;
        FileNode tmp;
        Stylesheet s;

        orig = OS.CURRENT.lineSeparator.join(lines);
        tmp = (FileNode) world.getTemp().createTempFile().writeLines(lines);
        s = Main.parse(mapper, tmp.getAbsolute());
        tmp.delete();
        assertEquals(orig, Output.prettyprint(s).trim());
        assertEquals(compress(orig), Output.compress(s));
    }

    private String compress(String css) throws IOException {
        StringReader src;
        StringWriter dest;

        src = new StringReader(css);
        dest = new StringWriter();
        new CssCompressor(src).compress(dest, 30000);
        return dest.toString();
    }
}
