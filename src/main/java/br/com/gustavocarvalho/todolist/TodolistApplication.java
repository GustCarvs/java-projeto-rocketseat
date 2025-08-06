package br.com.gustavocarvalho.todolist; //toda classe se inicia com package de onde esta classe esta inserida.

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //gerencia todos os pacotes da Spring Aplication 
public class TodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args); //
	}

}
