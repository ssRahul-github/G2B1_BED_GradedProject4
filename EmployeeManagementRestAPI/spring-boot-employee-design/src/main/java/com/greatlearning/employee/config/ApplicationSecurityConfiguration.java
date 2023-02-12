package com.greatlearning.employee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter
{
     private final UserDetailsService userDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder builder) throws Exception {
		/*builder
		       .inMemoryAuthentication()
		           .withUser("Kiran")
		           .password("{noop}user")
		       .roles("USER")
		       .and()
		           .withUser("Vinay")
		           .password("{noop}welcome")
		       .roles("USER","ADMIN");*/
		         builder
		         .userDetailsService(this.userDetailsService)
		         .passwordEncoder(passwordEncoder());
	}
		@Override
		public void configure(HttpSecurity http) throws Exception {
			//authorization
			http.cors().disable();
			http.csrf().disable();
			http.headers().frameOptions().disable();
			http.authorizeRequests()
			    .antMatchers(HttpMethod.GET,"/api/employees/**")
			        .hasAnyRole("USER", "ADMIN","SUPERADMIN")
			    .antMatchers("/h2-console/**","/login**") 
			    .permitAll()
			    .and()
			    .authorizeRequests()
			    .antMatchers(HttpMethod.POST,"/api/employees/setuser/**")
				    .hasRole("SUPERADMIN")
				.antMatchers(HttpMethod.POST,"/api/employees/**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.PUT,"/api/employees/**")
					.hasRole("ADMIN")
				.antMatchers(HttpMethod.DELETE,"/api/employees/**")
					.hasRole("ADMIN")	
				.anyRequest().authenticated()
				.and()
				.formLogin()
				.and()
				.httpBasic()
				.and()
				/* Set the sessionCreationPolicy to avoid cookies for authentication*/
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
@Bean
public PasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder();
}
		
}
