package com.cls;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	protected void globalConfigure(AuthenticationManagerBuilder auth, DataSource dataSource) throws Exception {
		
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"select id as principal, password as credentials, actived from users where username=?")
				.authoritiesByUsernameQuery(
						"select users_id as principal, roles_roleName as role from users_roles where users_id=?")
				.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/css/**","/js/**","/images/**","/").permitAll()
				.anyRequest()
				.authenticated()
			.and()
				.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/")
					.failureUrl("/login?action=loginFail")
					.permitAll()
			.and()
				.logout()
					.invalidateHttpSession(true)
					.logoutUrl("/logout")
					.permitAll()
			.and()
				.rememberMe()
			.and()
				.exceptionHandling()
				.accessDeniedPage("/403");
	}

}
