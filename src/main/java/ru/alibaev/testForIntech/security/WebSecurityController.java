package ru.alibaev.testForIntech.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
@EnableWebSecurity
public class WebSecurityController extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		 http
	        .authorizeRequests()                                                                
	            .antMatchers("/resources/**", "/css/**", "/js/**", "/registration/**").permitAll()
	            .anyRequest().authenticated()
	        .and()
	        	.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/themes",true)
	        .and()
	        	.logout().logoutSuccessUrl("/login?logout")
	        .and()
	    		.exceptionHandling().accessDeniedPage("/403")
	    	.and()
	    		.csrf().disable()
	    		;
	}

 	 @Autowired
	 //Этот метод должен выглядеть именно так. Нельзя менять состав колонок в SQL-запросе.
 	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
 		 auth.jdbcAuthentication().dataSource(dataSource)
	 	  .usersByUsernameQuery("select login,password,enabled,authority_id from users where login=?")
	 	  .authoritiesByUsernameQuery("select login,authority_id from users where login=?")
	 	  .passwordEncoder(new BCryptPasswordEncoder());
 	 }
}
