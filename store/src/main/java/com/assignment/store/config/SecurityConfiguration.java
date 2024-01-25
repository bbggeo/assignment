package com.assignment.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration
        extends AbstractSecurityWebApplicationInitializer {

    public SecurityConfiguration() {
    }

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        User.UserBuilder users = User.withDefaultPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(users.username("finance").password("password").roles("PROMO_GUY").build());
        manager.createUser(users.username("admin").password("password").roles("USER","ADMIN").build());
        return manager;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {

       http.securityMatcher("/h2-console/**").authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers(new AntPathRequestMatcher("/h2-console/**"));
    }
}