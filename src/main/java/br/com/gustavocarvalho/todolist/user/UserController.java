package br.com.gustavocarvalho.todolist.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
    


@RestController  //controla todas as rotas/recursos de uma APIrest
@RequestMapping("/users")   
public class UserController {

    @Autowired //parte do spring que permite não ter que inicializar classes
    private IUserRepository userRepository;

    

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if(user != null){ //se a condição for true, o usuario ja existe
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }

        var passwodHashred = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()); //coleta a senha do usuario e faz a verificação

        userModel.setPassword(passwodHashred);//set a password com o que o user forneceu 
        


        var userCreated = this.userRepository.save(userModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }
}




