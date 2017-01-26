package br.com.springmvc.controllers;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.springmvc.models.PaymentData;
import br.com.springmvc.models.ShoppingCart;
import br.com.springmvc.service.IntegrandoComPagamento;

@Controller
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private ShoppingCart shoppingCart;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public DeferredResult<String> checkout() {
		// usando lambda para retorno de classe anonima
		// callable é igual runnable para uso de threads, e permite retorno

		// pegando o total da compra
		BigDecimal total = shoppingCart.getTotal();
		System.out.println("Configurando Pagamento");
		
		DeferredResult<String> result = new DeferredResult<>();
		IntegrandoComPagamento integrando = new IntegrandoComPagamento(result, total, restTemplate);

		Thread thread = new Thread(integrando);
		thread.start();
		return result;

	}
}
