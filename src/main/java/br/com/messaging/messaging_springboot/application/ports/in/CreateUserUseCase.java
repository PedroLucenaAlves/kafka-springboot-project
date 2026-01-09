package br.com.messaging.messaging_springboot.application.ports.in;


import br.com.messaging.messaging_springboot.domain.User;

/**
 * Os casos de uso descrevem como os atores (usuarios) irao interagir com o nosso sistema
 * As use cases ficam na porta de entrada pois elas orquestram a logica de negocio, traduzindo as requisioes que receberemos
 * de fora (um cadastro no front ou uma req do postman a nossa api) e interage com o nosso dominio.
 * Os Use Cases são a camada que fica logo "abaixo" dos adaptadores de entrada, no limite entre o mundo externo e o domínio.
 */

//Essa interface define o que o sistema sabe fazer
public interface CreateUserUseCase {

    void criarUsuario(User user);
}
