package br.com.messaging.messaging_springboot.infrastructure.adapters.input.web;

import br.com.messaging.messaging_springboot.application.ports.in.CreateUserUseCase;
import br.com.messaging.messaging_springboot.domain.User;
import br.com.messaging.messaging_springboot.infrastructure.adapters.input.web.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class HelloController {

    // O Controller agora depende do CASO DE USO, não do Service concreto
    private final CreateUserUseCase createUserUseCase;

    // O Spring injeta quem estiver implementando essa interface
    public HelloController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public String criarUsuario(@RequestBody UserDTO userDto) {
        //Converte DTO (Input Web) para Domínio
        User novoUser = new User(userDto.name(), userDto.email(), userDto.age());

        createUserUseCase.criarUsuario(novoUser);

        return "Usuário recebido e enviado para processamento!";
    }
}


    /**
     * ANTES
     * private final HelloProducer service;
     *
     *     public HelloController(HelloProducer service) {
     *         this.service = service;
     *     }
     */
