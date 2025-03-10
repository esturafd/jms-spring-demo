package com.example.demo.configs;

import com.example.demo.entities.User;
import com.example.demo.entities.UserDetailsImpl;
import com.example.demo.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableJpaAuditing
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired private UserRepository userRepository;
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/api/customer").hasRole("ADMIN")
            .antMatchers("/api/custtrans").hasAnyRole("ADMIN", "USER")
            .and()
            .httpBasic()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(getUserDetailsService())
            .passwordEncoder(getPasswordEncoder());
    }

    public UserDetailsService getUserDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        Iterable<User> users = userRepository.findAll();
        users.forEach(u -> manager.createUser(new UserDetailsImpl(u)));
        return manager;
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuditorAware<String> getAuditorAware() {
        return new SecurityAuditorAware();
    }
}