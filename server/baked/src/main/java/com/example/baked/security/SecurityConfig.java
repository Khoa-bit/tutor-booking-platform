package com.example.baked.security;

import com.example.baked.filter.CustomAuthenticationFilter;
import com.example.baked.filter.CustomAuthorizationFilter;
import com.example.baked.util.JWTUtil;
import com.example.baked.util.SecurityUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  public static final String AUTH_LOGIN_PATTERN = "/auth/login*";
  public static final String AUTH_TOKEN_REFRESH_PATTERN = "/auth/token/refresh*";
  public static final HashMap<HttpMethod, String[]> PERMIT_PATTERNS =
      new HashMap<>(
          Map.of(
              HttpMethod.POST,
              new String[] {
                AUTH_TOKEN_REFRESH_PATTERN, AUTH_LOGIN_PATTERN, "/api/users", "/auth/signin*"
              }));
  private final UserDetailsService userDetailsService;
  private final JWTUtil jwtUtil;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(SecurityUtil.passwordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable();
    //    http.authorizeRequests().anyRequest().permitAll();

    CustomAuthenticationFilter customAuthenticationFilter =
        new CustomAuthenticationFilter(this.authenticationManagerBean(), jwtUtil);
    customAuthenticationFilter.setFilterProcessesUrl("/auth/login");

    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests()
        .antMatchers(HttpMethod.POST, PERMIT_PATTERNS.get(HttpMethod.POST))
        .permitAll();
    // http.authorizeRequests().antMatchers(HttpMethod.POST,
    // "/api/user/save/**").hasAuthority("ROLE_ADMIN");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(customAuthenticationFilter);
    http.addFilterBefore(
        new CustomAuthorizationFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowCredentials(true);
    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
    configuration.setAllowedHeaders(List.of("authorization", "content-type", "x-auth-token"));
    configuration.setExposedHeaders(List.of("x-auth-token"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);
    return source;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }
}
