package com.mason.blog.oauth2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.
		authorizeRequests()
				//.antMatchers("/wh/api/artical/**","/wh/api/sort/**","wh/api/message/**").permitAll()
				.antMatchers("/api/artical/**","/api/sort/**","/api/message/**").permitAll()
				//.antMatchers("/artical/**","/sort/**","/message/**","/api/message/list/").permitAll()
				.antMatchers("/usernamepassword/token").permitAll()
				//貌似对新增资源没有什么影响？
				.antMatchers("/users/**","/menus/**","/roles/**","/upload/**").hasRole("ADMIN")
				.anyRequest()
				.authenticated();
		/*
		* The ResourceServerConfigurerAdapter is configured for different endpoints (see antMatchers) than the WebSecurityConfigurerAdapter.

The difference between those two adapters is, that the ResourceServerConfigurerAdapter uses a special filter that checks for the bearer token in the request to authenticate the request via OAuth2.

The WebSecurityConfigurerAdapter is used to authenticate the user via a session (form login in the case of your given examples).*/
	}

}
