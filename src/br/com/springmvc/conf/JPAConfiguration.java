package br.com.springmvc.conf;

import java.sql.DriverManager;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement		//Habilitando o controle transacional do Spring
public class JPAConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {

		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		/*
		 * A classe LocalContainerEntitymanagerFactoryBean é a abstracao do
		 * persistence.xml que geralmente é necessario para termos o JPA
		 * funcionando em nosso projeto
		 * 
		 */
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[]{"br.com.springmvc.models"});

		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		/*
		 * HibernateJpaVendorAdapter representa a escolha de implementaçaõ do
		 * jpa, no caso do nosso projeto, o Hibernate
		 */
		em.setJpaVendorAdapter(vendorAdapter);
		em.setJpaProperties(additionalProperties());

		return em;
	}

	@Bean // Essa anotation indica que os objetos vao ser gerenciados pelo spring e poderam ser injetados
	public DataSource dataSource() {
		// Metodo necessario para configurar os parametros de conexao com o
		// banco de dados

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/springmvc");
		dataSource.setUsername( "root" );
		dataSource.setPassword( "admin" );
		return dataSource;

	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		/*
		 * A classe PlatformTransactionManager é responsavel do spring para transacoes, nesse caso 
		 * usado a implementaçção para JPA
		 */
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(emf);
		return transactionManager;

	}

	private Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.setProperty("hibernate.show_sql", "true");
		return properties;
	}

}
