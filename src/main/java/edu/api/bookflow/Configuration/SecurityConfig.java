/*
package edu.api.bookflow.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

*/
/**
 * Esta classe permite a configuração do CORS para casos de testes da aplicação
 * Futuramente será necessário, e MUITO recomendado,
 * aplicar medidas de segurança mais robustas, como armazenamento seguro de senhas
 * e políticas de CORS adequadas.
 * **//*

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    */
/**
     * Adicionando um usuário teste para as requisições
     * !não usar em produção!
     *
     * Esse usuário ficará em memória para possíveis testes
     * com o {noop} ele indica que a senha não possui nenhuma codificação
     * e seu papel é "user"
     * **//*

    @Bean
    InMemoryUserDetailsManager users(){
        return new InMemoryUserDetailsManager(
                User.withUsername("username")
                        .password("{noop}password")
                        .roles("USER")
                        .build()
        );
    }

    */
/**
     * Configurando cadeias de filtro de segurança
     * @return http - retorna a configuração de segurança
     * **//*

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults()) // por padrão, usa um Bean com o nome de corsConfigurationSource
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated()) // exige autenticação para qualquer requisição
                .httpBasic(Customizer.withDefaults()) // Habilita autenticação HTTP Basic, que é um método simples de autenticação usando cabeçalhos HTTP.
                .build();
    }

    */
/**
     * Configurando política de CORS
     *
     * **//*

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // somente requisições deste endereço serão aceitas (localhost:3000)
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        // lista de metodos http permitidos
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT", "PUT"));
        // lista de cabeçalhos permitidos nas requisições
        configuration.setAllowedHeaders(List.of("Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // instanciando a configuração de CORS para todas as rotas ("/")
        source.registerCorsConfiguration("/", configuration);
        return source;
    }

}
*/
