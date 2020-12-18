package com.jrp.pma.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, enabled "+
		"from user_accounts where username = ?")
		.authoritiesByUsernameQuery("select username, role "+
		"from user_accounts where username = ?")
		.passwordEncoder(bCryptEncoder);
		
//		.withDefaultSchema() //creates authorities and users table from default spring schema
//		.withUser("myuser")
//			.password("pass")
//			.roles("USER")
//		.and()
//		.withUser("taz")
//			.password("pass")
//			.roles("USER")
//		.and()
//		.withUser("manager")
//			.password("pass")
//			.roles("ADMIN");
	}
	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/projects/new").hasRole("ADMIN")
		.antMatchers("/employees/new").hasRole("ADMIN")
		.antMatchers("/projects/save").hasRole("ADMIN")
		.antMatchers("/employees/save").hasRole("ADMIN")
		.antMatchers("/", "/**").permitAll()
		//.antMatchers("/h2-console/**").permitAll()
		//.antMatchers("/").authenticated()
		.and().formLogin();
		
		http.csrf().disable(); //to do redirects
		http.headers().frameOptions().disable();
	}

}
