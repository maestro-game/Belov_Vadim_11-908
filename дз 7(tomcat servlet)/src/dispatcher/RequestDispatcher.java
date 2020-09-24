package dispatcher;

import html.HtmlGenerator;
import html.Page;

import java.io.Writer;
import java.util.Arrays;

public class RequestDispatcher implements Dispatcher{
    private HtmlGenerator htmlGenerator;


    public void dispatch (String request, Writer writer) {
        String[] req = request.split("/");
        Page page;
        try {
            page = Page.valueOf(req[1]);
        } catch (IllegalArgumentException e) {
            htmlGenerator.generate(Page.notFound, writer);
            return;
        }

        if (page == Page.id) {
            if (req.length < 3) {
                htmlGenerator.generate(Page.notFound, writer);
            } else {
                try {
                    htmlGenerator.generate(Page.id, Integer.parseInt(req[2]), writer);
                } catch (NumberFormatException e) {
                    htmlGenerator.generate(Page.notFound, writer);
                }
            }
        } else {
            htmlGenerator.generate(page, writer);
        }
    }

    public RequestDispatcher(HtmlGenerator htmlGenerator) {
        this.htmlGenerator = htmlGenerator;
    }
}
