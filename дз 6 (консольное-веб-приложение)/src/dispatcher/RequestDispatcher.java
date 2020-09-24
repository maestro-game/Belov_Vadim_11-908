package dispatcher;

import html.HtmlGenerator;
import html.Page;

public class RequestDispatcher implements Dispatcher{
    private HtmlGenerator htmlGenerator;


    public void dispatch (String request) {
        String[] req = request.split("/");
        Page page;
        try {
            page = Page.valueOf(req[1]);
        } catch (IllegalArgumentException e) {
            htmlGenerator.generate(Page.notFound);
            htmlGenerator.show();
            return;
        }

        if (page == Page.id) {
            if (req.length < 3) {
                htmlGenerator.generate(Page.notFound);
            } else {
                try {
                    htmlGenerator.generate(Page.id, Integer.parseInt(req[2]));
                } catch (NumberFormatException e) {
                    htmlGenerator.generate(Page.notFound);
                }
            }
        } else {
            htmlGenerator.generate(page);
        }
    }

    public RequestDispatcher(HtmlGenerator htmlGenerator) {
        this.htmlGenerator = htmlGenerator;
    }
}
