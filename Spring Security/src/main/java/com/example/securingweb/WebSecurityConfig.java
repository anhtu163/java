package com.example.securingweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SimpleAuthenticationSuccessHandler successHandler;

    public WebSecurityConfig(SimpleAuthenticationSuccessHandler successHandle) {
        this.successHandler = successHandle;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr)
        throws Exception {
        authenticationMgr.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN").and()
        .withUser("user").password(passwordEncoder().encode("user")).authorities("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/user.html").hasRole("USER")
                .antMatchers("/admin.html").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().successHandler(successHandler)
				.and().logout().permitAll();
    }

    /*@Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder().username("username").password("123456").roles("USER").build();
        return new InMemoryUserDetailsManager(user);
    }*/
}
