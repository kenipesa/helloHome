package com.kenipesa.helloHome.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserDetailsServiceImpl userDetailsService;
  
  @Bean
  public PasswordEncoder passwordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }
  
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
  
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http
     .cors().disable()
     .csrf().disable()
     .authorizeRequests()
     .antMatchers("/", "/login", "/register", "/error", "/aboutUs", "/styles.css", "/LI-In-Bug.png", "/Octocat.png",
      "/team-picture.jpg")
     .permitAll()
     .antMatchers(HttpMethod.POST, "/user/*")
     .permitAll()
     .anyRequest()
     .authenticated()
     .and()
     .formLogin()
     .loginPage("/login")
      .defaultSuccessUrl("/user/profile")
     .and()
     .logout()
      .logoutSuccessUrl("/");
  }
  
  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}