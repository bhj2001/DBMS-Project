package com.example.dbms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SequrityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.userDetailsService(userDetailsService);

        // auth.inMemoryAuthentication()
        //     .withUser("blah")
        //     .password("blah")
        //     .roles("CLIENT");
        
        // auth.inMemoryAuthentication()
        //     .withUser("foo")
        //     .password("foo")
        //     .roles("ADMIN");
        
        // auth.inMemoryAuthentication()
        //     .withUser("bar")
        //     .password("bar")
        //     .roles("EMPLOYEE");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
            .antMatchers("/employee/**").hasAnyAuthority("EMPLOYEE")
            .antMatchers("/client/**").hasAnyAuthority("CLIENT")
            .antMatchers("/**").permitAll()
            .and().formLogin();
    }
}