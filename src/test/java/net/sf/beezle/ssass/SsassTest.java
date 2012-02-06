package net.sf.beezle.ssass;

import com.yahoo.platform.yui.compressor.CssCompressor;
import net.sf.beezle.mork.mapping.Mapper;
import net.sf.beezle.mork.misc.GenericException;
import net.sf.beezle.ssass.scss.Output;
import net.sf.beezle.ssass.scss.Stylesheet;
import net.sf.beezle.sushi.fs.World;
import net.sf.beezle.sushi.fs.file.FileNode;
import net.sf.beezle.sushi.io.OS;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
    public void term1() throws IOException {
        check("term1 {",
              "  number: 42;",
              "  percentage: 2%;",
              "  length-px: 3px;",
              "  length-cm: 4cm;",
              "  length-mm: 5mm;",
              "  length-in: 6in;",
              "  length-pt: 7pt;",
              "  length-pc: 8pc;",
              "  a: -3;",
              "  b: +4%;",
              "  c: -100cm;",
              "  d: 12em;",
              "  e: 13ex;",
              "  f: 20deg;",
              "  g: 21rad;",
              "  h: 22grad;",
              "  i: 0s;",
              "  j: 1ms;",
              "  k: 2hz;",
              "  l: 3khz",
              "}");
    }

    @Test
    public void term2() throws IOException {
        check("term2 {",
              "  string1: \"str\";",
              "  string2: 'str';",
              "  ident: ident;",
              "  url: url(foo);",
              "  hexcolor: #name;",
              "  function: foo(bar)",
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
    public void complex() throws IOException, GenericException {
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

    //-- ssass

    @Test
    public void variable() throws IOException {
        sass(
                "$blue: \"abc\";\n" +
                ".content-navigation {\n" +
                "  border-color: $blue;\n" +
                "}\n",
                ".content-navigation {\n" +
                "  border-color: \"abc\"\n" +
                "}\n"
                );
    }

    @Test
    public void nestedRuleset() throws IOException {
        sass(  "table.hl {\n" +
                "  margin: 2em 0;\n" +
                "  td.ln {\n" +
                "    text-align: right;\n" +
                "  }\n" +
                "}",

                "table.hl {\n" +
                "  margin: 2em 0\n" +
                "}\n" +
                "table.hl td.ln {\n" +
                "  text-align: right\n" +
                "}\n");
    }

    @Test
    public void nestedRulesetTwice() throws IOException {
        sass(  "top {\n" +
                "  a: b;\n" +
                "  mid {\n" +
                "    foo: bar;\n" +
                "    bottom {\n" +
                "      x: y;\n" +
                "    }\n" +
                "  }\n" +
                "}",
                "top {\n" +
                "  a: b\n" +
                "}\n" +
                "top mid {\n" +
                "  foo: bar\n" +
                "}\n" +
                "top mid bottom {\n" +
                "  x: y\n" +
                "}\n");
    }

    @Test
    public void nestedProperties() throws IOException {
        sass(   "li {\n" +
                "  font: {\n" +
                "    family: serif;\n" +
                "    weight: bold;\n" +
                "    size: 1.2em;\n" +
                "  }\n" +
                "}",
                "li {\n" +
                "  font-family: serif;\n" +
                "  font-weight: bold;\n" +
                "  font-size: 1.2em\n" +
                "}\n");
    }

    @Test
    public void nestedPropertiesTwice() throws IOException {
        sass(   "top {\n" +
                "  a: 1;\n" +
                "  mid: {\n" +
                "    b: 2;\n" +
                "    bottom: {\n" +
                "      c: 3;\n" +
                "    }\n" +
                "  }\n" +
                "}",
                "top {\n" +
                "  a: 1;\n" +
                "  mid-b: 2;\n" +
                "  mid-bottom-c: 3\n" +
                "}\n");
    }

    @Test
    public void mixinProperties() throws IOException {
        sass   ("@mixin left {\n" +
                "  float: left;\n" +
                "}\n" +
                "\n" +
                "#data {\n" +
                "  @include left;\n" +
                "}",
                "#data {\n" +
                "  float: left\n" +
                "}\n");
    }

    @Test
    public void mixinRuleset() throws IOException {
        sass   ("@mixin table-base {\n" +
                "  th {\n" +
                "    text-align: center;\n" +
                "    font-weight: bold;\n" +
                "  };\n" +
                "  td, th {padding: 2px}\n" +
                "}\n" +
                "#data {\n" +
                "  @include table-base;\n" +
                "}",

                "#data {\n" +
                "\n" +
                "}\n" +
                "#data th {\n" +
                "  text-align: center;\n" +
                "  font-weight: bold\n" +
                "}\n" +
                "#data td, #data th {\n" +
                "  padding: 2px\n" +
                "}\n");
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
        try {
            assertEquals(orig, Output.prettyprint(s).trim());
            assertEquals(compress(orig), Output.compress(s));
        } catch (GenericException e) {
            fail(e.getMessage());
        }
    }

    private String compress(String css) throws IOException {
        StringReader src;
        StringWriter dest;

        src = new StringReader(css);
        dest = new StringWriter();
        new CssCompressor(src).compress(dest, 30000);
        return dest.toString();
    }

    private void sass(String sass, String css) throws IOException {
        FileNode tmp;
        Stylesheet s;

        tmp = (FileNode) world.getTemp().createTempFile().writeString(sass);
        s = Main.parse(mapper, tmp.getAbsolute());
        if (s == null) {
            throw new IOException("syntax error");
        }
        tmp.delete();
        try {
            assertEquals(css, Output.prettyprint(s));
        } catch (GenericException e) {
            fail(e.getMessage());
        }
    }
}
