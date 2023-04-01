package com.example.linkedhuceng.security;

import com.example.linkedhuceng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import static com.example.linkedhuceng.security.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    private static final String[] WHITELIST = {"/error", "/signup", "/icons/*", "/img/*", "/css/*",
            "/js/*", "/verifyRegistration", "/verificationSent", "/admin/manage-accounts", "/admin/enrollments",
            "/admin/reported-accounts", "/admin/reported-groups", "/admin/reported-posts"};

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // order of ant-matchers matter

        http
                .cors().and().csrf().disable()  // don't disable if it is a browser application
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .authorizeRequests()
                .antMatchers(WHITELIST).permitAll()       // whitelist these pages and resources
                .antMatchers("/admin/*").hasRole(ADMIN.name())                  // allow only admins to access these pages
                .anyRequest()                                                             // any request must be:
                .authenticated()                                                          // authenticated (need username and password)
                .and()
                .formLogin()                                                              // enforce authenticity by using Form Based Auth
                    .loginPage("/login").permitAll()
                    .successHandler(myAuthenticationSuccessHandler())
                    .passwordParameter("password")
                    .usernameParameter("username")
                .and()
//                .rememberMe()
//                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(30))
//                    .rememberMeParameter("remember-me")
//                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        System.out.println(userService);
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}
