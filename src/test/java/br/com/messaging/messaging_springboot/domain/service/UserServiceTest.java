package br.com.messaging.messaging_springboot.domain.service;

import br.com.messaging.messaging_springboot.application.ports.out.UserRepositoryOut;
import br.com.messaging.messaging_springboot.application.service.UserService;
import br.com.messaging.messaging_springboot.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock //cria um duble para simular o banco de dados de pe
    private UserRepositoryOut userRepositoryOut;

    @InjectMocks //chama o service e injeta o duble dentro dele
    UserService userService;

    @Test
    @DisplayName("Deve chamar a porta de saída para salvar o usuário")
    void deveSalvarUsuarioComSucesso() {
        // 1. ARRANGE (Cenário)
        // Criamos um usuário
        User userParaSalvar = new User("Pedro Teste", "pedro@teste.com", 25);

        // 2. ACT (Ação)
        userService.criarUsuario(userParaSalvar);

        // 3. ASSERT (Verificação de Comportamento)
        // A tradução disso é: "Verifique se o método save() do userRepositoryOut foi chamado 1 vez com esse usuário"
        verify(userRepositoryOut, times(1)).save(userParaSalvar);
    }

}
