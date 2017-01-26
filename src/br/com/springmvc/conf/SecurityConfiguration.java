package br.com.springmvc.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//Configurando e ensinando o filtro
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService users;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Metodo responsavel pelas configuracoes do filtro
		http.authorizeRequests().antMatchers("/produtos/form").hasRole("ADMIN").antMatchers("/shopping/**")
		.permitAll().antMatchers(HttpMethod.POST,"/produtos").hasRole("ADMIN").antMatchers("/produtos/**")
		.permitAll().anyRequest().authenticated().and().formLogin();
		/*
		 * antMatchers("/produtos/form").hasRole(ADMIN) alem de estar logado, o usuario precisa ser admin 
		 * para acessar este endereço da nossa aplicação
		 * 
		 * antMatchers(HttpMethod.POST,"/produtos").hasRole("ADMIN") caso seja acessado pelo verbo POST, só liberamos 
		 * o acesso caso tenha sido disparado por um usuacio com perfil admin
		 * 
		 * antMatchers("/produtos/**")
		.permitAll() todos os enderecos começados com /produtos são permitidos
		primeiros as restricoes  e depois libere o resto
		 */
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		//Sobrecarga 
		auth.userDetailsService(users).passwordEncoder(new BCryptPasswordEncoder());
		//Passando o criador de authenticacao e colocando BCrypt como codificador do password
		
		
	}
}
