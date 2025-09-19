package br.com.gustavocarvalho.todolist.Filter;

import java.io.IOException;
import java.util.Base64;

import org.hibernate.annotations.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.gustavocarvalho.todolist.user.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component // utiliza quando desejar que o spring gerencia a clase
public class FilterTaskAuth extends OncePerRequestFilter { // filter do spring ja com os tipos http

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/")) {
            // pegar a autenticação
            var authorization = request.getHeader("Authorization");

            var authEncoded = authorization.substring("Basic".length()).trim(); // substring: um parâmetro - o programa
                                                                                // "corta" o primeiro parâmetro
                                                                                // fornecido

            byte[] authDecode = Base64.getDecoder().decode(authEncoded);

            var authString = new String(authDecode);

            // ["guga", "12345"]
            String[] credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            System.out.println("Authorization");
            System.out.println(username);
            System.out.println(password);

            // validar o usuário
            var user = this.userRepository.findByUsername(username);
            if (user == null) {
                response.sendError(401);
            } else {
                // validar senha
                var passworVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
                if (passworVerify.verified) {
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401);
                }
                // executa a api
                
            }

        }else{
            filterChain.doFilter(request, response);    
        }

    }

}
