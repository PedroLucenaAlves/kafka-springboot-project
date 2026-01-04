package br.com.messaging.messaging_springboot.kafkacomponents;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Este é o componente que publica o evento
 */

@Service
public class HelloProducer {

    //este objeto foi criado para interagir com o kafka enviando os eventos
    private KafkaTemplate<String, String> kafkaTemplate;

    public HelloProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    //metodo para enviar mensagem ao seu topico setado
    public void sendMessage(String message){
        kafkaTemplate.send("hello-topic", message);
    }

}