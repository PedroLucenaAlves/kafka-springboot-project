package br.com.messaging.messaging_springboot.infrastructure.adapters.input.stream;

import br.com.messaging.messaging_springboot.application.ports.in.CreateUserUseCase;
import br.com.messaging.messaging_springboot.domain.User;
import br.com.messaging.messaging_springboot.infrastructure.adapters.input.stream.dto.UserJsonDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class UserKafkaAdapter {

    // Dependência do "Cardápio" (Porta de Entrada)
    private final CreateUserUseCase createUserUseCase;

    // Ferramenta para ler o JSON (pois configuramos StringDeserializer no YAML)
    private final ObjectMapper objectMapper;

    public UserKafkaAdapter(CreateUserUseCase createUserUseCase, ObjectMapper objectMapper) {
        this.createUserUseCase = createUserUseCase;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "hello-topic", groupId = "group-1")
    public void consumirMensagem(String mensagemJson) {
        try {
            System.out.println("🦁 KAFKA ADAPTER: Recebi a mensagem bruta: " + mensagemJson);

            // Converter String JSON -> DTO (ou Classe Auxiliar)
            UserJsonDTO dto = objectMapper.readValue(mensagemJson, UserJsonDTO.class);

            // Converter DTO -> DOMÍNIO (Regra de Ouro: O Adapter converte pra linguagem do Domínio)
            User userDomain = new User(dto.name(),dto.email(), dto.age());

            //Chamar o Caso de Uso (O Chef)
            createUserUseCase.criarUsuario(userDomain);

            System.out.println("✅ KAFKA ADAPTER: Usuário enviado para o UseCase com sucesso!");

        } catch (JsonProcessingException e) {

            System.err.println("❌ JSON Inválido (Sem Retry): " + e.getMessage());

        } catch (Exception e) {
            System.err.println("❌ Erro no processamento: " + e.getMessage());

            throw new RuntimeException(e);
        }
    }

}

