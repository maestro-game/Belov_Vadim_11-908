package html;

public enum Page {
    home("home.ftl"),
    id("profile.ftl"),
    login("login.ftl"),
    messages("messages.ftl"),
    notFound("notFound.ftl"),
    register("register.ftl");

    String path;

    Page(String path) {
        this.path = path;
    }
}
