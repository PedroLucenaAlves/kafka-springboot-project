package br.com.messaging.messaging_springboot.application.ports.out;

import br.com.messaging.messaging_springboot.domain.User;

/**
 * Port faz a ponte entre o domain e o adapter, repassando os dados do nosso nucleo e solicitando alguem para que
 * execute algo (algum adapter implementara essa interface)
 */

// Note que usamos o User do DOMAIN, não o do Banco de Dados
public interface UserRepositoryOut {
        User save(User user);
}
