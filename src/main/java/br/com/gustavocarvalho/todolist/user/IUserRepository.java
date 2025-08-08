package br.com.gustavocarvalho.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface IUserRepository extends JpaRepository<UserModel, UUID>{ //(interface)Modelo dentro da aplicação. extende repositorio de metodos para o user
    UserModel findByUsername(String username);
}
