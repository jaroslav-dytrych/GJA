/*
 * Adapted from http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
 */

package cz.vutbr.fit.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  DataSource dataSource;

  @Autowired
  public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

    auth.jdbcAuthentication().passwordEncoder(passwordEncoder()).dataSource(dataSource)
      .usersByUsernameQuery("select username,password, enabled from users where username=?")
      .authoritiesByUsernameQuery("select username, role from user_roles where username=?");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.authorizeRequests()
      .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
      .and()
      .formLogin().loginPage("/login").failureUrl("/login?error")
      .usernameParameter("username").passwordParameter("password")
      .and()
      .logout().logoutSuccessUrl("/login?logout")
      .and()
      .exceptionHandling().accessDeniedPage("/403")
      .and()
      .csrf();
  }
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    // This PasswordEncoder is provided for legacy and testing purposes only 
    // and is not considered secure. A password encoder that does nothing.
    @SuppressWarnings("deprecation") 
    PasswordEncoder pe = NoOpPasswordEncoder.getInstance();
    
    return pe;
  }
}
