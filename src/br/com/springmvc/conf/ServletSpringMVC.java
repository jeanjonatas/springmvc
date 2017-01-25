package br.com.springmvc.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//Classe respons�vel por tratar todas as requisi��es que chegam ao spring mvc
public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Metodo para configurar quais os controllers devem ser mapeados e quais classes devem ser carregadas
		return new Class[]{AppWebConfiguration.class, JPAConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO  Metodo para mostrar qual o padrao de endereco da servlet, semelhante ao url mapping
		return new String[] {"/"};
	}

}
