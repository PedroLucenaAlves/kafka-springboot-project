package br.com.messaging.messaging_springboot.application.service;

import br.com.messaging.messaging_springboot.application.ports.in.CreateUserUseCase;
import br.com.messaging.messaging_springboot.application.ports.out.UserRepositoryOut;
import br.com.messaging.messaging_springboot.domain.User;
import org.springframework.stereotype.Service;

@Service // O Spring gerencia esse cara
public class UserService implements CreateUserUseCase {

    /**
     O serivce aponta para a interface setando tudo que ele sabe fazer
    exp: um cliente (usuario) nao fala direto ao chef, ele analisa o cardapio (usecase) para saber o que o chef
    (service) pode fazer, facilitando ele ter que questionar sobre 1000 pratos ao chef (analisando linha a linha do
    service).

     o Adapter (Cliente) não precisa saber como o Chef corta a cebola (Lógica) e nem onde ele guarda
     os ingredientes (Banco). Ele só faz o pedido.
     * */

        // O Serviço conversa com a Porta de Saída
        private final UserRepositoryOut userRepositoryOut;

        // Injeção de dependência via construtor
        public UserService(UserRepositoryOut userRepositoryOut) {
            this.userRepositoryOut = userRepositoryOut;
        }

        @Override
        public void criarUsuario(User user) {

            System.out.println("Domain: Recebi o usuário " + user.getName() + ". Mandando salvar...");

            userRepositoryOut.save(user);
        }

}
