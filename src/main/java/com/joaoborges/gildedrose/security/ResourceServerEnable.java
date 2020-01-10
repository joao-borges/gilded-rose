
package com.joaoborges.gildedrose.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Enable authentication using the token server foi the existing endpoints.
 *
 * @author joaoborges
 */
@Configuration
@EnableResourceServer
public class ResourceServerEnable extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/inventory").permitAll()
                .antMatchers("/api/buy/**")
                .authenticated();
    }

}
