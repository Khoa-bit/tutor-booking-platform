package com.example.baked.security;

import com.example.baked.filter.CustomAuthenticationFilter;
import com.example.baked.filter.CustomAuthorizationFilter;
import com.example.baked.service.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String AUTH_LOGIN_PATTERN = "/auth/login/**";
  public static final String AUTH_TOKEN_REFRESH_PATTERN = "/auth/token/refresh/**";
  private final UserDetailsService userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncode;
  private final JWTService jwtService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncode);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter =
        new CustomAuthenticationFilter(authenticationManagerBean(), jwtService);
    customAuthenticationFilter.setFilterProcessesUrl("/auth/login");
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers(AUTH_LOGIN_PATTERN, AUTH_TOKEN_REFRESH_PATTERN)
        .permitAll();
    //    http.authorizeRequests().antMatchers(HttpMethod.GET,
    // "/api/user/**").hasAuthority("ROLE_USER");
    //    http.authorizeRequests().antMatchers(HttpMethod.POST,
    // "/api/user/save/**").hasAuthority("ROLE_ADMIN");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(customAuthenticationFilter);
    http.addFilterBefore(
        new CustomAuthorizationFilter(jwtService), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
