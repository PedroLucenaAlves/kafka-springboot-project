package br.com.messaging.messaging_springboot.infrastructure.adapters.output.persistence;

import br.com.messaging.messaging_springboot.application.ports.out.UserRepositoryOut;
import br.com.messaging.messaging_springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Pega o pedido do domínio, converte para UserEntity e grava no banco
 * funciona como um cabo adaptador que captura as infos de saída (port) e converte para o uso adequado
 */

@Component // O Spring gerencia esse cara
public class UserPersistenceAdapter implements UserRepositoryOut {

    @Autowired
    private SpringDataUserRepository repository;

    @Override
    public User save(User userDomain) {
        // Traduzindo do Domínio (Limpo) para a Entidade (Suja)
        UserEntity entity = new UserEntity(
                userDomain.getName(),
                userDomain.getEmail(),
                userDomain.getAge()
        );

        UserEntity salvo = repository.save(entity);

        return userDomain;
    }

}

