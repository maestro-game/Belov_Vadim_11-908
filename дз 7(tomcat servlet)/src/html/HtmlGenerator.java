package html;

import java.io.Writer;

public interface HtmlGenerator {
    String PATH = ".temp.html";

    void generate(Page page, Writer writer);

    void generate(Page page, int param, Writer writer);
}
