package command;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class RequestWrapper extends AbstractRequestWrapper {

    private Map<String, String> params = new HashMap<String, String>();
    private HttpSession session = new SessionWrapper();

    public void addParam(String key, String value) {
        params.put(key, value);
    }

    public HttpSession getSession() {
        return session;
    }

    public String getParameter(String s) {
        return params.get(s);
    }


}


