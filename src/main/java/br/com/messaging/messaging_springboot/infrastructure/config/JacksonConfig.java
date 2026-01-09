package br.com.messaging.messaging_springboot.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe para corrigir o erro:
 * "Consider defining a bean of type 'com.fasterxml.jackson.databind.ObjectMapper' in your configuration."
 */

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        // Cria e disponibiliza o Jackson para quem pedir (como seu Kafka Adapter)
        return new ObjectMapper();
    }

}
