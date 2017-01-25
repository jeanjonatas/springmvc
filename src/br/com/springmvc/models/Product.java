package br.com.springmvc.models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Entity		//JPA, indica que essa classe vai virar uma tabela
public class Product {
	
	@Id 		//Indica que o atributo em questão é chave primaria
	@GeneratedValue		//Indica como vai ser gerada a chave primaria
	private Integer id;
	@NotBlank
	private String title;
	@Lob
	@NotBlank
	private String description;
	@Min(30)
	private int pages;
	@ElementCollection		
	private List<Price> prices = new ArrayList<Price>();
	@DateTimeFormat(iso=ISO.DATE)
	private Calendar releaseDate;
	
	private String summaryPath;
	

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Price> getPrices() {
		return prices;
	}

	public void setPrices(List<Price> prices) {
		this.prices = prices;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String texto = "O livro " + this.getTitle() + " com a descricao " + this.getDescription() + " e" + " com "
				+ this.getPages() + " paginas";

		return texto;
	}

	public Calendar getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Calendar releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getSummaryPath() {
		return summaryPath;
	}

	public void setSummaryPath(String summaryPath) {
		this.summaryPath = summaryPath;
	}

}
