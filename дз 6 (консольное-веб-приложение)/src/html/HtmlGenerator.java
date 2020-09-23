package html;

import data.DataSource;
import models.Message;
import models.Post;

import java.io.IOException;
import java.util.List;

public interface HtmlGenerator {
    String PATH = ".temp.html";

    void generate(Page page, DataSource dataSource);

    void generate(Page page, int param, DataSource dataSource);

    void show(Page page);
}
