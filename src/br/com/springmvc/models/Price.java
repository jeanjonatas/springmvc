package br.com.springmvc.models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Price {
	
	@Column(scale = 2)
	private BigDecimal value;
	private BookType bookType;

	// Enum sao usados em estrutura de dados para guarda valores com sentido
	// organizado

	public enum BookType {
		EBOOK,PRINTED,COMBO
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BookType getBookType() {
		return bookType;
	}

	public void setBookTpe(BookType bookTpe) {
		this.bookType = bookTpe;
	}

}
