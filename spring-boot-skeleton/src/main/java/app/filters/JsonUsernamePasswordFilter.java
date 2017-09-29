package app.filters;

import app.security.LoginFailureHandler;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JsonUsernamePasswordFilter extends GenericFilterBean {

    private AntPathRequestMatcher matcher;

    public JsonUsernamePasswordFilter() {
        this.matcher = new AntPathRequestMatcher("/rest/login", "POST");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!matcher.matches((HttpServletRequest)request)) {
            chain.doFilter(request, response);
            return;
        }
        try {
            chain.doFilter(new JsonUsernamePasswordRequestWrapper((HttpServletRequest) request), response);
        } catch (AuthenticationException e) {
            new LoginFailureHandler().onAuthenticationFailure((HttpServletRequest) request, (HttpServletResponse) response, e);
        }
    }

}