package br.com.messaging.messaging_springboot.controller;

import br.com.messaging.messaging_springboot.dto.UserDTO;
import br.com.messaging.messaging_springboot.kafkacomponents.HelloProducer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class HelloController {

    private final HelloProducer service;

    public HelloController(HelloProducer service) {
        this.service = service;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody UserDTO user) {
        service.sendMessage(user);
        return "Usuário enviado para o Kafka!";
    }

}
