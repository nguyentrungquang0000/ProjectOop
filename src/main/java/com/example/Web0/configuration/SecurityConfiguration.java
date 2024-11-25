package com.example.Web0.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfiguration {
    private final String SEY_KEY="LmCvzYozUk1bEXD5xIXJxqz00lRjdFNJ";
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(requests ->
                        requests
                                .requestMatchers(HttpMethod.POST, "/api/token").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/introspect").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/users").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/search").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/myinfo").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/register").permitAll()
                                .requestMatchers(HttpMethod.GET, "/login", "/register", "/", "/menu", "/detail**", "/api/cartitem/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")
                                .requestMatchers("/css/**", "/js/**", "/assets/**", "/img/**").permitAll()
                                .anyRequest().authenticated())
                        .formLogin(formLogin -> formLogin
                                        .loginPage("/")
                                .loginProcessingUrl("/login")
                                .successHandler((request, response, authentication) -> {
                                    if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
                                        redirectStrategy.sendRedirect(request, response, "/admin/");
                                    } else {
                                        redirectStrategy.sendRedirect(request, response,"/");
                                    }
                                })
                                        .permitAll())
                                .logout(logout -> logout.logoutUrl("/logout")
                                        .logoutSuccessUrl("/")
                                        .permitAll());
//        httpSecurity
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.decoder(jwtDecoder())));

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        return httpSecurity.build();
    }


//    @Bean
//    JwtDecoder jwtDecoder() {
//        SecretKeySpec secretKeySpec = new SecretKeySpec(SEY_KEY.getBytes(), "HS256");
//        return NimbusJwtDecoder
//                .withSecretKey(secretKeySpec)
//                .macAlgorithm(MacAlgorithm.HS256)
//                .build();
//    }


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return daoAuthenticationProvider;
    }

}
