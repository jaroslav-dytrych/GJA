/*
 * Adapted from http://javahash.com/spring-security-hello-world-example/
 */

package cz.vutbr.fit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication().withUser("tom").password("{noop}123456").roles("USER");
    auth.inMemoryAuthentication().withUser("bill").password("{noop}123456").roles("ADMIN");
    auth.inMemoryAuthentication().withUser("james").password("{noop}123456").roles("SUPERADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
      .antMatchers("/protected/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_SUPERADMIN')")
      .antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')")
      .and().formLogin().and().logout();

  }
}
