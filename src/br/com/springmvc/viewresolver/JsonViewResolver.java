package br.com.springmvc.viewresolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class JsonViewResolver implements ViewResolver {

	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		// devo retornar um objeto do tipo view q sera responsavel por escrever
		// a resposta do cliente
		MappingJackson2JsonView view = new MappingJackson2JsonView();
		view.setPrettyPrint(true);
		//Configurando uma chave de acesso, dessa forma o json ignora todas as outras chaves
		view.setModelKey("product");
		return view;
	}

}
