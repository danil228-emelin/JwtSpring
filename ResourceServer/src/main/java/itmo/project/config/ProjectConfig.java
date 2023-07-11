package itmo.project.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class ProjectConfig {
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    private OtpAuthenticationProvider otpAuthenticationProvider;
    private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

    @Bean("initialFilter")
    public InitialAuthenticationFilter initialAuthenticationFilter(AuthenticationManager authManager) {
        return new InitialAuthenticationFilter(authManager);
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(usernamePasswordAuthenticationProvider);
        authenticationManagerBuilder.authenticationProvider(otpAuthenticationProvider);
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,InitialAuthenticationFilter initialAuthenticationFilter) throws Exception {
        http.addFilterAt(initialAuthenticationFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(jwtAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeHttpRequests(x -> x.anyRequest().authenticated());
        return http.build();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}