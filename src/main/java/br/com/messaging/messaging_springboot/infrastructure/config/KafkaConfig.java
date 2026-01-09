package br.com.messaging.messaging_springboot.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

/**
 * Classe criada para tratarmos os eventos mandados p DQL (um termo antigo dos correios que enviava uma carta ao remetente,
 * e caso ele estivesse morto ou o endereço estivesse errado, ele deixava "armazenado" para os dados não se perderem ate o
 * responsavel se manifestar)
 */

@Configuration
public class KafkaConfig {

    // Essa configuração define: "Se der erro, tente de novo. Se falhar tudo, mande pra DLQ"
    //se o banco falhar ele tenta 3x, se nao funcionar ele manda para um topico de mensagens mortas
    @Bean
    public DefaultErrorHandler errorHandler(KafkaTemplate<Object, Object> template) {

        // DLQ Recoverer recebe a mensagem morta
        // Ele vai mandar para o tópico original + ".DLQ" (ex: hello-topic.DLQ)
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(template);

        // Regra de Tentativas (Backoff)
        // Tente 3 vezes, esperando 1 segundo (1000ms) entre cada tentativa
        FixedBackOff backOff = new FixedBackOff(1000L, 3);

        //cria o handler
        return new DefaultErrorHandler(recoverer, backOff);
    }
}
