package dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Dispatcher {
    void dispatch(HttpServletRequest request, HttpServletResponse writer);
}
