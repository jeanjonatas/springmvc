package br.com.springmvc.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.springmvc.daos.ProductDAO;
import br.com.springmvc.infra.FileSaver;
import br.com.springmvc.models.Price.BookType;
import br.com.springmvc.models.Product;

@Controller
@Transactional
@RequestMapping("/produtos") // Essa anotacao serve para indicar que minha
								// classe precisa de
// transacao
public class ProductController {

	@Autowired // Essa anotacao serve para indicar os pontos de injecao da minha
				// classe
	private ProductDAO productDAO;
	
	@Autowired
	private FileSaver fileSaver;
	
	@RequestMapping(method = RequestMethod.POST, name="saveProduct") // Post usado quando há necessidade de criação de
	@CacheEvict(value="books", allEntries=true)			//Evita cache ao entrar esse metodo e invalida todos no cache
	public ModelAndView save(MultipartFile summary,@Valid Product product,BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()){
			return form(product);
		}
		
		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		
		System.out.println(summary.getName() + ";" +summary.getOriginalFilename());
		
		productDAO.save(product);
		
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		
		System.out.println(product);
		
		return new ModelAndView("redirect:produtos"); 
		
		/* Má pratica realizar um forward apos ter sido feito um post
		 Prefixo redirect indica para o spring mvc que em vez de simplesmente
		 executar um forward ele
		 retorna um 302 pra o navegador solicitando q o mesmo faca um novo
		 request
		*/
	}

	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		/*
		 * A classe ModelView tem metodos para adicionar objetos na view Em vez
		 * de retornar um objeto, posso passar um parametro
		 * 
		 */

		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("types", BookType.values()); // Exatamente como o
															// setAtributte()
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET) // Get usado quando quero
	@Cacheable(value="books")										// recuperar uma informacao
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/show")
	public ModelAndView show(Integer id){
		
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		return modelAndView;
	}
	
	/*
	@RequestMapping(method=RequestMethod.GET, value="json")
	@ResponseBody		//O retorno do metodo e para ser usado diretamente como corpo de resposta
	public List<Product> listJson(){
		
		return productDAO.list();
	}
	*/
}
