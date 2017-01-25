package br.com.springmvc.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.springmvc.controllers.HomeController;
import br.com.springmvc.daos.ProductDAO;

/*
 *	Objetivo principal é expor para a servlet do Spring MVC quais as classes devem ser lidas  e
 *carregadas 
 * 
 * */
@EnableWebMvc
@ComponentScan(basePackageClasses = { HomeController.class, ProductDAO.class }) // indico quais pacotes devem ser lidos
public class AppWebConfiguration {

	@Bean		//Essa anotation diz que o retorno desse metodo deve ser registrado como um objeto gerenciado pelo container
	public InternalResourceViewResolver internalResourceViewResolver() {
		/*
		 * metodo da classe que guarda as informações da pasta base e sufixo que
		 * devem ser adicionados para qualquer caminho que sejam adicionados
		 */
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
		
	@Bean
	public MessageSource messageSource(){
		//Metodo responsavel por buscar e configurar as messages 
		ReloadableResourceBundleMessageSource bundle = new ReloadableResourceBundleMessageSource();
		bundle.setBasename("WEB-INF/messages");		//Adiciona a localizacao das messages
		bundle.setDefaultEncoding("UTF-8");		//Default codificacao de caracter
		bundle.setCacheSeconds(1);			//Arquivo recarregado a cada 1 s, cach
		return bundle;
	}
}
