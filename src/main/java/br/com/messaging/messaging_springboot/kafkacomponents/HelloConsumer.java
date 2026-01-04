package br.com.messaging.messaging_springboot.kafkacomponents;

import br.com.messaging.messaging_springboot.dto.UserDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * O consumidor é o cara interessado em ler o topico enviado pelo producer
 */

@Service
public class HelloConsumer {

    //para que o metodo saiba automaticamente qual mensagem do kafka e o topico ele deve receber, setamos essa info
    @KafkaListener(topics = "hello-topic", groupId = "group-1")
    public void receiveMessage(UserDTO user){
        System.out.println("Nível 1 Concluído! Recebi o usuário: " + user.toString());
        System.out.println("E-mail do usuário: " + user.getEmail());
    }

}
