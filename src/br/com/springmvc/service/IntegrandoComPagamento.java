package br.com.springmvc.service;

import java.math.BigDecimal;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.async.DeferredResult;

import br.com.springmvc.models.PaymentData;

public class IntegrandoComPagamento implements Runnable {

	private BigDecimal value;
	private DeferredResult<String> result;
	private RestTemplate restTemplate;

	public IntegrandoComPagamento(DeferredResult<String> result, BigDecimal total, RestTemplate restTemplate) {
		// TODO Auto-generated constructor stub
		super();
		this.value = total;
		this.result = result;
		this.restTemplate = restTemplate;

	}

	@Override
	public void run() {
		// TODO roda a thread
		System.out.println("Execucao da thread");
		
		
		try{
		String uriToPay = "http://localhost:9000/payment";
		// Passando os parametros para o servidor de pagamento
		
			//mandando post
			String response = restTemplate.postForObject(uriToPay, new PaymentData(value), String.class);
			result.setResult("redirect:/payment/sucess");
		} catch (HttpClientErrorException e) {
			result.setResult("redirect:/payment/error");

		}
	}

}
