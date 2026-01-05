package br.com.messaging.messaging_springboot.kafkacomponents;

import br.com.messaging.messaging_springboot.dto.UserDTO;
import br.com.messaging.messaging_springboot.entity.UserEntity;
import br.com.messaging.messaging_springboot.repository.UserRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * O consumidor é o cara interessado em ler o topico enviado pelo producer
 */

@Service
public class HelloConsumer {

    private final UserRepository userRepository;

    public HelloConsumer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @KafkaListener(topics = "hello-topic", groupId = "group-2")
    public void receiveMessage(UserDTO userDTO) {
        //Converter DTO para Entity
        UserEntity entity = new UserEntity(userDTO.getName(), userDTO.getAge(), userDTO.getEmail());

        //Salvar no Banco
        UserEntity salvo = userRepository.save(entity);

        System.out.println("Salvo no Banco! ID: " + salvo.getId());
    }

    //NVL 1
    //para que o metodo saiba automaticamente qual mensagem do kafka e o topico ele deve receber, setamos essa info
//    @KafkaListener(topics = "hello-topic", groupId = "group-1")
//    public void receiveMessage(UserDTO user){
//        System.out.println("Nível 1 Concluído! Recebi o usuário: " + user.toString());
//        System.out.println("E-mail do usuário: " + user.getEmail());
//    }

}
