package html;

public interface HtmlGenerator {
    String PATH = ".temp.html";

    void generate(Page page);

    void generate(Page page, int param);

    void show();
}
