package com.timespace.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.core.userdetails.User.builder;


@EnableWebMvc
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter 
{

	   public SpringSecurityConfig() 
	   {
	        super();
	    }


	    @Override
	    protected void configure(HttpSecurity httpSecurity) throws Exception
	    {
	    	httpSecurity
	              
	                .authorizeRequests()
	                .antMatchers("/h2-console/**").permitAll()
	                .antMatchers("/humanresource/**").hasRole("HR")
	                .antMatchers("/employee/**").hasAnyRole("HR","EMPLOYEE")
	                .antMatchers("/shared/**").hasAnyRole("EMPLOYEE","MANAGER")
	            .and()
	                .formLogin()
	                .failureUrl("/login-error")
	            .and()
	                .logout()
	                .logoutSuccessUrl("/")
	            
	            .and()
	                .exceptionHandling()
	                .accessDeniedPage("/403");
	        
	        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
            			.authorizeRequests().antMatchers("/console/**").permitAll();
	        httpSecurity.csrf().disable();
	        httpSecurity.headers().frameOptions().disable();

	    }
	    

		@Bean
		public PasswordEncoder passwordEncoder(){
			return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}

		@Bean
		public UserDetailsService userDetailsService() {
			return new InMemoryUserDetailsManager(
				builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("user")
					.password("123")
					.roles("EMPLOYEE")
					.build(),
				builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("admin")
					.password("password")
					.roles("ADMIN")
					.build(),
					builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("hr")
					.password("123")
					.roles("HR")
					.build(),
					builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("manager")
					.password("123")
					.roles("EMPLOYEE", "MANAGER")
					.build()
			);
		}

	}

