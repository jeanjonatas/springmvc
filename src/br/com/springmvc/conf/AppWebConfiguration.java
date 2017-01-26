package br.com.springmvc.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.springmvc.controllers.HomeController;
import br.com.springmvc.daos.ProductDAO;
import br.com.springmvc.infra.FileSaver;
import br.com.springmvc.models.ShoppingCart;
import br.com.springmvc.viewresolver.JsonViewResolver;

/*
 *	Objetivo principal é expor para a servlet do Spring MVC quais as classes devem ser lidas  e
 *carregadas 
 * 
 * */
@EnableWebMvc
@ComponentScan(basePackages = "br.com.springmvc") // indico quais pacotes devem ser lidos
@EnableCaching
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
		//nome da classe que será vista e posso usar com diferente escopo na jsp
		resolver.setExposedContextBeanNames("shoppingCart");
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
	
	public FormattingConversionService mvcConversionService(){
			/*O nome do método tem que ser mvcConversionService
			 * DefaultFormatterRegistrar implementa a interface FormatterRegister que deve ser usada quando
			 * e necessario agrupar varios tipos de conversoes
			 * setFormatter indica qual conversao padrao deve ser realizada
			 * registerFormatter registra todos os conversores, ele é instanciando com um true no construtor
			 * para aplicar todos os conversores como padrao
			 * */
		
		
			DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
			
			//Habilitando sempre o dateformat para o padrao
			DateFormatterRegistrar registrar = new DateFormatterRegistrar();
			registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
			registrar.registerFormatters(conversionService);
			
			return conversionService;
	}
	@Bean
	public	MultipartResolver	multipartResolver(){
				return	new	StandardServletMultipartResolver();
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	@Bean
	public CacheManager cacheManager(){
		//construtor de cache com o tamanho maximo de 5 min
		
		CacheBuilder<Object, Object> builder = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(builder);
		return cacheManager;
		
	}
	@Bean
	public ViewResolver contentNegotiationViewResolver(ContentNegotiationManager manager){
		List<ViewResolver> resolvers = new ArrayList<>();
		
		//Peguei o mesmo objeto do retorno de internalResource
		resolvers.add(internalResourceViewResolver());
		resolvers.add(new JsonViewResolver());
		//Adicionei um objewto de json na list
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		
		return resolver;
		
		
	}
}
