package app.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;

class JsonUsernamePasswordRequestWrapper extends HttpServletRequestWrapper {

    private HashMap<String, String> params = new HashMap<>();

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    JsonUsernamePasswordRequestWrapper(HttpServletRequest request) throws AuthenticationException {
        super(request);

        try {
            params = new ObjectMapper().readValue(request.getInputStream(), HashMap.class);
        } catch (Exception e) {
            return;
        }
    }

    @Override
    public String getParameter(String name) {
        return params.getOrDefault(name, "");
    }

}