package com.loginservices.configuration;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.loginservices.filter.AuthoritiesLoggingAfterFilter;
import com.loginservices.filter.AuthoritiesLoggingAtFilter;
import com.loginservices.filter.JWTTokenGeneratorFilter;
import com.loginservices.filter.JWTTokenValidatorFilter;
import com.loginservices.filter.RequestValidationBeforeFilter;


@Configuration
public class MyConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/**
		 * Default configurations which will secure all the requests
		 */

		/*
		 * http .authorizeRequests() .anyRequest().authenticated() .and()
		 * .formLogin().and() .httpBasic();
		 */

		/**
		 * Custom configurations as per our requirement
		 */

		/*
		 * http .authorizeRequests() .antMatchers("/myAccount").authenticated()
		 * .antMatchers("/contact").permitAll() .and() .formLogin().and() .httpBasic();
		 */

		/**
		 * Configuration to deny all the requests
		 */

		/*
		 * http .authorizeRequests() .anyRequest().denyAll() .and() .formLogin().and()
		 * .httpBasic();
		 */

		/**
		 * Configuration to permit all the requests
		 */

//		http .authorizeRequests() .anyRequest().permitAll().and() .formLogin().and()
//		 .httpBasic();

//		http.authorizeRequests().antMatchers("/myAccount").authenticated().antMatchers("/myBalance").authenticated()
//		.antMatchers("/myLoans").authenticated().antMatchers("/myCards").authenticated().antMatchers("/notices")
//		.permitAll().antMatchers("/contact").permitAll().and().formLogin().and().httpBasic();

		
		
		//http.authorizeRequests().antMatchers("/contact").authenticated().and().formLogin().and().httpBasic();
		
		
		//http.cors().configurationSource(new CorsConfigurationSource() {
		// NOT TO USE DEFUALT JSESSION ID from Spring and TO USE JWT
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().
		cors().configurationSource(new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
				config.setAllowedMethods(Collections.singletonList("*"));
				config.setAllowCredentials(true);
				config.setAllowedHeaders(Collections.singletonList("*"));
				config.setExposedHeaders(Arrays.asList("Authorization"));
				config.setMaxAge(3600L);
				return config;
			}
		}).and().csrf().disable()
		
		.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
		.addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/myAccount").authenticated()
		.antMatchers("/myBalance").authenticated()
		.antMatchers("/myLoans").authenticated()
		.antMatchers("/myCards").authenticated()
		//.antMatchers("/user").authenticated()
		.antMatchers("/studentlogin").authenticated()
		//.antMatchers("/createstudent").authenticated()
		.antMatchers("/deletestudent").authenticated()
		.antMatchers("/deletestudentbyid").authenticated()
		.antMatchers("/notifications").hasAuthority("READ")
		.antMatchers("/myvideos").hasAnyRole("USER")
		.antMatchers("/notices").permitAll()
		.antMatchers("/contact").permitAll().and().httpBasic();
		
	}

	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception {
	 * auth.inMemoryAuthentication().withUser("admin").password("12345").authorities
	 * ("admin").and(). withUser("user").password("12345").authorities("read").and()
	 * .passwordEncoder(NoOpPasswordEncoder.getInstance()); }
	 */

	
	/*
	 * @Override protected void configure(AuthenticationManagerBuilder auth) throws
	 * Exception { InMemoryUserDetailsManager userDetailsService = new
	 * InMemoryUserDetailsManager(); UserDetails user =
	 * User.withUsername("admin1").password("12345").authorities("admin").build();
	 * UserDetails user1
	 * =User.withUsername("user1").password("12345").authorities("read").build();
	 * userDetailsService.createUser(user); userDetailsService.createUser(user1);
	 * auth.userDetailsService(userDetailsService); }
	 */
	  
		// this is to use Exact User created in Spring boot and our Database 
	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */
		 
	 
	  // If this is not there you will get error
		@Bean 
		public PasswordEncoder passwordEncoder() {
			//return NoOpPasswordEncoder.getInstance();
			return new BCryptPasswordEncoder();
		}

}
