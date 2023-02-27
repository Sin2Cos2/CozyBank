package com.sin2cos2.springsecurity.config;

import com.sin2cos2.springsecurity.filters.CsrfCookieFilter;
import com.sin2cos2.springsecurity.filters.JWTTokenGeneratorFilter;
import com.sin2cos2.springsecurity.filters.JWTTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    // JWT authorization with cors and csrf
    @Bean
    SecurityFilterChain defaultSecurityFilterChat(HttpSecurity http) throws Exception {
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf");

        CorsConfigurationSource corsConfigurationSource = request -> {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
            configuration.setAllowedMethods(Collections.singletonList("*"));
            configuration.setAllowCredentials(true);
            // Header for JWT. Letting ui app know we are sending a JWT token
            // Spring security is taken care of csrf token, we don't have to add any headers
            configuration.setExposedHeaders(Collections.singletonList("Authorization"));
            configuration.setAllowedHeaders(Collections.singletonList("*"));
            configuration.setMaxAge(3600L);
            return configuration;
        };

        // Spring is not creating any sessions anymore
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors().configurationSource(corsConfigurationSource)
                .and().csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
                // Generating token if client access /user path
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                // Validating token for every path except /user
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests()
                .requestMatchers("/user").authenticated()
                .requestMatchers("/account", "/balance", "/loans", "/cards").hasRole("USER")
                .requestMatchers("/notices", "/contact", "/register").permitAll()
                .and().formLogin()
                .and().httpBasic();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Session authorization with cors and csrf
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChat(HttpSecurity http) throws Exception {
//        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
//        requestHandler.setCsrfRequestAttributeName("_csrf");
//
//        CorsConfigurationSource corsConfigurationSource = request -> {
//            CorsConfiguration configuration = new CorsConfiguration();
//            configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//            configuration.setAllowedMethods(Collections.singletonList("*"));
//            configuration.setAllowCredentials(true);
//            configuration.setAllowedHeaders(Collections.singletonList("*"));
//            configuration.setMaxAge(3600L);
//            return configuration;
//        };
//
//        http.securityContext().requireExplicitSave(false)
//                // Сессия не создается по стандарту, невозможно создать сессионную аутенфикацию без кода снизу
//                // Настройка чтобы создавалась сессия если это необходимо
//                .and().sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
//                .cors().configurationSource(corsConfigurationSource)
//                .and().csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
//                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
//                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
//                .authorizeHttpRequests()
//                .requestMatchers("/account", "/balance", "/loans", "/cards", "/user").hasRole("USER")
//                .requestMatchers("/notices", "/contact", "/register").permitAll()
//                .and().formLogin()
//                .and().httpBasic();
//
//        return http.build();
//    }

    // NOT RECOMMENDED
    /* Variant 1
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("12345")
                .authorities("read")
                .build();

        return new  InMemoryUserDetailsManager(admin, user);
    }
    */

   /* Variant 2
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.withUsername("admin")
                .password("12345")
                .authorities("admin")
                .build();
        UserDetails user = User.withUsername("user")
                .password("12345")
                .authorities("read")
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }

   */

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
}
