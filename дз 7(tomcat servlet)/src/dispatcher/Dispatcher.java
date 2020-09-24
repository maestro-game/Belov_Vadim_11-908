package dispatcher;

import java.io.Writer;

public interface Dispatcher {
    public void dispatch(String request, Writer writer);
}
