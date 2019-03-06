package command;

import java.util.HashMap;
import java.util.Map;

public class SessionWrapper extends AbstractSessionWrapper {
    private Map<String, Object> values = new HashMap<>();

    public Object getAttribute(String s) {
        return values.get(s);
    }

    public void setAttribute(String s, Object o) {
        values.put(s, o);
    }

    public void removeAttribute(String s) {
        values.remove(s);
    }

}


