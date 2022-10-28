package br.com.serratec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SegurancaConfig extends WebSecurityConfigurerAdapter {
    
    
    // REALIZAR AUTENTICACAO
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth.inMemoryAuthentication()
        .withUser("maria")
        .password("{noop}123")
        .roles("Admin");
        
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
        .antMatchers(HttpMethod.DELETE,"/produtos").denyAll()
        .antMatchers(HttpMethod.GET,"/produtos").permitAll()
        .and()
        .httpBasic();      
    }

}