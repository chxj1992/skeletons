package app.filters;

import app.exceptions.AuthenticationException;
import app.utils.AuthUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class BeforeLogoutFilter extends GenericFilterBean {

    private static final Logger logger = LoggerFactory.getLogger(BeforeLogoutFilter.class);

    private AntPathRequestMatcher matcher;


    public BeforeLogoutFilter() {
        this.matcher = new AntPathRequestMatcher("/rest/logout", "POST");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!matcher.matches((HttpServletRequest) request)) {
            chain.doFilter(request, response);
            return;
        }

        try {
            AuthUtil.currentUser();
        } catch (AuthenticationException e) {
            logger.info("already logged out");
        }
        chain.doFilter(request, response);
    }

}