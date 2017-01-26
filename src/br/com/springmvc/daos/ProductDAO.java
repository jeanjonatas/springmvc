package br.com.springmvc.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.springmvc.models.Product;

//Classe responsavel pela conexao com banco de dados de produto
@Repository 	//Anotacao para indicar que essa classe é responsavel pela conexao com o banco de dados
public class ProductDAO {
	
	@PersistenceContext		//anotacao pedindo a injecao e EntityManager
	private EntityManager manager;
	
	public void save(Product product) {
		manager.persist(product);
	}

	public List<Product> list() {
		// TODO Auto-generated method stub
		return manager.createQuery("select distinct(p) from"
				+ " Product p join fetch p.prices", Product.class).getResultList();
	}

	public Product find(Integer id) {
		//Fazendo uma busca no banco pelo id
		TypedQuery<Product> query = manager
				.createQuery(
						"select distinct(p) from Product p join fetch p.prices where p.id=:id",
						Product.class).setParameter("id", id);
		return query.getSingleResult();
	}
	
}
