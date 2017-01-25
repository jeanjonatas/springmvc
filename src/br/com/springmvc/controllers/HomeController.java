package br.com.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller // Mostrando que essa classe serve para atender as requisições do cliente
public class HomeController {

	@RequestMapping("/") // anotação para saber qual url o metodo chama
	public String index() {
		// metodo gerado para carregar os produtos
		System.out.println("Carregando os produtos");
		return "hello-world";

	}

}
