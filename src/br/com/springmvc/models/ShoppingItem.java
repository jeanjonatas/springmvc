package br.com.springmvc.models;

import java.math.BigDecimal;
import java.util.Arrays;

import br.com.springmvc.models.Price.BookType;

public class ShoppingItem {

	private Product product;
	private BookType bookType;
	private Integer productId;
	
	/*
	 *metodo estaticos da classe
	 * 
	 * 
	 * */
	 //comeca um produto do zero
	public static ShoppingItem zeroed(){
		Product product = new Product();
		Price price = new Price();
		BookType combo = BookType.COMBO;
		price.setBookType(combo);
		price.setValue(BigDecimal.ZERO);
		product.setPrices(Arrays.asList(price));
		return new ShoppingItem(product, combo);
		
	}
	//Cria um produto e pega o id do produto
	public ShoppingItem(Product product, BookType bookType) {
		this.product = product;
		this.bookType = bookType;
		this.productId = product.getId();
	}
	
	
	public Product getProduct() {
		return product;
	}
	
	public BookType getBookType() {
		return bookType;
	}
	
	//Explicacao desse metodo em product
	public BigDecimal getPrice(){
		/** Navegue também até a classe Product, para descobrir como é a implementação do método priceFor **/
		return product.priceFor(bookType);
	}

	@Override
	public int hashCode() {
		//Cria um hash padrao e retorna o resultado
		
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookType == null) ? 0 : bookType.hashCode());
		result = prime * result
				+ ((productId == null) ? 0 : productId.hashCode());
		

		return result;
	
	}
	//Apenas sobreescreve o equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ShoppingItem other = (ShoppingItem) obj;
		if (bookType != other.bookType)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}

	
	public BigDecimal getTotal(Integer quantity) {
		return getPrice().multiply(new BigDecimal(quantity));
	}

	
	

}