package br.unisales.gestaohorarios.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilita CSRF para permitir POST/PUT/DELETE em APIs REST
                .headers(headers -> headers.frameOptions(frame -> frame.disable())) // Permite renderizar o console do H2 em iFrame
                .authorizeHttpRequests(auth -> auth
                        // Endpoints públicos (Swagger UI, documentação OpenAPI e Console H2)
                        .requestMatchers(
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()
                        // Todos os outros endpoints (/api/**) exigem autenticação
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()); // Habilita autenticação HTTP Basic

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        // Usuário padrão em memória para realizar os testes da API
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123") // {noop} indica senha sem criptografia para ambiente de desenvolvimento
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(admin);
    }
}
