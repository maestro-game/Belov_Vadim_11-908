package html;

import data.DataManager;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;
import models.User;

import javax.servlet.ServletContext;
import java.io.*;
import java.util.Map;

public class HTMLManager implements HtmlGenerator {
    private final DataManager dataManager;

    public void render(Page page, ServletContext servletContext, Writer writer, Map<String, Object> root) {
        render(page, null, servletContext, writer, root);
    }

    public void render(Page page, String param, ServletContext servletContext, Writer writer, Map<String, Object> root) {
        switch (page) {
            case messages -> {
                root.put("massages", dataManager.getMessages());
            }
            case id -> {
                User user = dataManager.getUser(param);
                if (user == null) {
                    page = Page.notFound;
                } else {
                    root.put("profile", user);
                    root.put("posts", dataManager.getPosts(param));
                }
            }
        }

        try {
            ((Configuration) servletContext.getAttribute("cfg")).getTemplate(page.path).process(root, writer);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }

    public HTMLManager(DataManager dataManager) {
        this.dataManager = dataManager;
    }
}
