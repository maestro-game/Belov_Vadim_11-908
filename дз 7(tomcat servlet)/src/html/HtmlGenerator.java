package html;

import javax.servlet.ServletContext;
import java.io.Writer;
import java.util.Map;

public interface HtmlGenerator {
    String PATH = ".temp.html";

    void render(Page page, ServletContext servletContext, Writer writer, Map<String, Object> root);

    void render(Page page, String param, ServletContext servletContext, Writer writer, Map<String, Object> root);
}
