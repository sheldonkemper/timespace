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
	                .antMatchers("/").permitAll()
	                .antMatchers("/console/**").permitAll()
	                .antMatchers("/humanresource/**").hasRole("HR")
	                .antMatchers("/employee/**").hasAnyRole("ACCOUNT","HR","EMPLOYEE","MANAGER")
	                .antMatchers("/manager/**").hasAnyRole("MANAGER")
	                .antMatchers("/account/**").hasAnyRole("ACCOUNT")
	                
	            .and()
	                .formLogin()
	                .successHandler(new LogonSuccessHandler())
	                .failureUrl("/login-error")
	            .and()
	                .logout()
	                .logoutSuccessUrl("/")
	            
	            .and()
	                .exceptionHandling()
	                .accessDeniedPage("/403");
	        
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
					.username("Boland")
					.password("12345")
					.roles("EMPLOYEE","ACCOUNT")
					.build(),
				builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("Versey")
					.password("12345")
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
					.username("Mallet")
					.password("12345")
					.roles("HR")
					.build(),
				builder()
					.passwordEncoder(input -> passwordEncoder().encode(input))
					.username("Kemper")
					.password("12345")
					.roles("EMPLOYEE", "MANAGER")
					.build()
			);
		}

	}

