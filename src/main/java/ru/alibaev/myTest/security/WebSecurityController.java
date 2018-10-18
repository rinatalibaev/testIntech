package ru.alibaev.myTest.security;


import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Controller;


@Controller
@EnableWebSecurity
public class WebSecurityController extends WebSecurityConfigurerAdapter {

	//Из-за того, что раньше наследовался от WebSecurityConfiguration и из-за отсутствия authenticated()
	//после anyRequest() все аутентифицированные пользователи имели доступ к любой странице.
	// Вот так выглядел код, когда был неработающим:
	// protected void configure(HttpSecurity http) throws Exception {
	//	   http.authorizeRequests()
	//	  .anyRequest().permitAll()
	////	  .antMatchers("/users/getAll").access("hasRole('ROLE_ADMIN')") 
	//	  .antMatchers("/users/getAll").hasAuthority("ROLE_ADMIN")
	//	  .and()
	//	    .formLogin().loginPage("/login")
	//	    .usernameParameter("name").passwordParameter("password")
	//	  .and()
	//	    .logout().logoutSuccessUrl("/login?logout") 
	//	   .and()
	//	   .exceptionHandling().accessDeniedPage("/403")
	//	  .and()
	//	    .csrf();
	//	 }
	
	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 
		 http
	        .authorizeRequests()                                                                
	            .antMatchers("/documents/**","/resources/**", "/signup", "/about").permitAll()
	            .antMatchers("/administrator/**").hasRole("ADMIN") 
	            .antMatchers("/users/**").hasRole("ADMIN")
	            .antMatchers("/documents/**").hasRole("ADMIN") 
	            .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
	            .anyRequest().authenticated()
	        .and()
	        	.formLogin()
	        .and()
	        	.logout().logoutSuccessUrl("/login?logout") 
	        .and()
	    		.exceptionHandling().accessDeniedPage("/403")
	    	.and()
	    		.csrf().disable()
	    		//Из-за того, что CSRF был включен не работали методы POST
	    		;
	}
		 
	
 	 @Autowired
 	 public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
 		 auth.jdbcAuthentication().dataSource(dataSource)
	 	  .usersByUsernameQuery("select name,password,enabled from users where name=?")
	 	  .authoritiesByUsernameQuery("select username, role from user_roles where username=?")
	 	  .passwordEncoder(new BCryptPasswordEncoder());
 	 } 
 	
}
