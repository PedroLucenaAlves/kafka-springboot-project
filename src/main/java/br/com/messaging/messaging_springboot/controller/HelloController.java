package br.com.messaging.messaging_springboot.controller;

import br.com.messaging.messaging_springboot.kafkacomponents.HelloProducer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka")
public class HelloController {

    private final HelloProducer service;

    public HelloController(HelloProducer service) {
        this.service = service;
    }

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        service.sendMessage("Hello: " + name);
        return "OK";
    }
}
