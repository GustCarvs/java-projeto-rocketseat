package br.com.gustavocarvalho.todolist.Filter;

import java.io.IOException;
import java.util.Base64;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component //utiliza quando desejar que o spring gerencia a clase 
public class FilterTaskAuth extends OncePerRequestFilter{  //filter do spring ja com os tipos http

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                //pegar a autenticação  
                var authorization = request.getHeader("Authorization");
                
                var authEncoded = authorization.substring("Basic".length()).trim(); //substring: um parâmetro - o programa "corta" o primeiro parâmetro fornecido 

                byte[] authDecode = Base64.getDecoder().decode(authEncoded);

                var authString = new String(authDecode);

                
                // ["guga", "12345"]
                String[] credentials = authString.split(":");
                String username = credentials[0];
                String password = credentials[1];
                
                System.out.println("Authorization");
                System.out.println(username);
                System.out.println(password);
                
                //validar o usuário 

                //validar senha 

                //executa a api 

                filterChain.doFilter(request, response);  
       
    }


}
