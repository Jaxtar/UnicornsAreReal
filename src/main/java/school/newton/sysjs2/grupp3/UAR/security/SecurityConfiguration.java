package school.newton.sysjs2.grupp3.UAR.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String LOGIN_PROCESSING_URL = "/login";
    private static final String LOGIN_FAILURE_URL = "/login?error";
    private static final String LOGIN_URL = "/login";
    private static final String LOGOUT_SUCCESS_URL = "/";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/movies", "/booking", "/booking/successful").permitAll()
                .antMatchers("/login", "/staff").access("hasRole('USER')")
                .and()
                .requestCache().requestCache(new StaffRequestCache())
                .and().authorizeRequests()
                .requestMatchers(SecurityUtils::isFrameworkInternalRequest).permitAll()
                .and().formLogin()
                .loginPage(LOGIN_URL).permitAll()
                .loginProcessingUrl(LOGIN_PROCESSING_URL)
                .defaultSuccessUrl("/staff/schedule", true)
                .failureUrl(LOGIN_FAILURE_URL)
                .and().logout().logoutSuccessUrl(LOGOUT_SUCCESS_URL);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("almc").password("{noop}almc1").roles("USER").build();
        UserDetails user2 = User.withUsername("yuna").password("{noop}yuna1").roles("USER").build();
        UserDetails user3 = User.withUsername("svva").password("{noop}svva1").roles("USER").build();
        UserDetails user4 = User.withUsername("anan").password("{noop}anan1").roles("USER").build();
        UserDetails user5 = User.withUsername("alno").password("{noop}alno1").roles("USER").build();
        UserDetails user6 = User.withUsername("kepa").password("{noop}kepa1").roles("USER").build();
        UserDetails user7 = User.withUsername("misa").password("{noop}misa1").roles("USER").build();
        UserDetails user8 = User.withUsername("erye").password("{noop}erye1").roles("USER").build();
        UserDetails user9 = User.withUsername("wawi").password("{noop}wawi1").roles("USER").build();
        UserDetails user10 = User.withUsername("elwi").password("{noop}elwi1").roles("USER").build();
        UserDetails user11 = User.withUsername("frga").password("{noop}frga1").roles("USER").build();
        UserDetails user12 = User.withUsername("lisa").password("{noop}lisa1").roles("USER").build();
        UserDetails user13 = User.withUsername("user").password("{noop}password").roles("USER").build();

        return new InMemoryUserDetailsManager(user, user2, user3, user4, user5, user6, user7, user8,
                user9, user10, user11, user12, user13);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                "/VAADIN/**",
                "/favicon.ico",
                "/robots.txt",
                "/manifest.webmanifest",
                "/sw.js",
                "/offline.html",
                "/icons/**",
                "/images/**",
                "/styles/**",
                "/h2-console/**");
    }
}
