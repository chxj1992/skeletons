package app.configs;

import app.filters.BeforeLogoutFilter;
import app.filters.JsonUsernamePasswordFilter;
import app.security.AuthenticationEntryPoint;
import app.security.LoginFailureHandler;
import app.security.LoginSuccessHandler;
import app.security.LogoutSuccessHandler;
import app.utils.CryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;

    private AuthenticationEntryPoint authenticationEntryPoint;
    private LoginSuccessHandler loginSuccessHandler;
    private LoginFailureHandler loginFailureHandler;
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setAuthenticationEntryPoint(AuthenticationEntryPoint authenticationEntryPoint) {
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Autowired
    public void setLoginSuccessHandler(LoginSuccessHandler loginSuccessHandler) {
        this.loginSuccessHandler = loginSuccessHandler;
    }

    @Autowired
    public void setLoginFailureHandler(LoginFailureHandler loginFailureHandler) {
        this.loginFailureHandler = loginFailureHandler;
    }

    @Autowired
    public void setLogoutSuccessHandler(LogoutSuccessHandler logoutSuccessHandler) {
        this.logoutSuccessHandler = logoutSuccessHandler;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
    }

    @Bean
    public DefaultCookieSerializer cookieSerializer() {

        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
        serializer.setUseHttpOnlyCookie(false);

        return serializer;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/rest/app/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/rest/admin/**").hasRole("ADMIN");

        // 关闭 CSRF 校验
        http.csrf().disable();

        // 采用默认跨域策略
        http.cors().configurationSource(request -> {
            CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
            configuration.addAllowedMethod("PUT");
            configuration.addAllowedMethod("DELETE");
            return configuration;
        });

        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);

        http.formLogin()
                .loginProcessingUrl("/rest/login")
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler);

        http.logout()
                .logoutUrl("/rest/logout")
                .logoutSuccessHandler(logoutSuccessHandler);

        http.addFilterBefore(new JsonUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new BeforeLogoutFilter(), LogoutFilter.class);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(CryptUtil.passwordEncoder());
    }

}