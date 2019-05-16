package com.dmarquina.kambistapayment;

import com.dmarquina.kambistapayment.util.JWTAuthorizationFilter;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@SpringBootApplication
public class KambistaPaymentApplication {

  public static void main(String[] args) {
    SpringApplication.run(KambistaPaymentApplication.class, args);
  }

  @EnableWebSecurity
  @Configuration
  class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.cors()
          .and()
          .csrf()
          .disable()
          .addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
          .authorizeRequests()
          .antMatchers("/login", "/v2/api-docs", "/configuration/ui", "/swagger-resources",
                       "/configuration/security", "/swagger-ui.html", "/webjars/**",
                       "/swagger-resources/configuration/ui", "/swagger-ui.html")
          .permitAll()
          .anyRequest()
          .authenticated();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
      CorsConfiguration configuration = new CorsConfiguration();
      configuration.setAllowedOrigins(Arrays.asList("*"));
      configuration.setAllowedMethods(
          Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
      configuration.setAllowedHeaders(
          Arrays.asList("authorization", "content-type", "x-auth-token"));
      configuration.setExposedHeaders(Arrays.asList("x-auth-token"));
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      source.registerCorsConfiguration("/**", configuration);
      return source;
    }
  }
}
