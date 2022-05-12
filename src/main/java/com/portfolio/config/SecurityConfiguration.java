package com.portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.portfolio.handle.MyAccessDeniedHandler;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception{
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http)throws Exception{
		//表單提交
				http.formLogin()
				//loginpage.html表單action 內容
					.loginProcessingUrl("/login")
					//自訂義登入頁面
					.loginPage("/loginpage")
					//登入成功之後要造訪的頁面
					.successForwardUrl("/page/home")
					//登入失敗之後要造訪的頁面
					.failureForwardUrl("/fail");
				
					//授權認證
				http.authorizeHttpRequests()
					//不需要被認證的頁面:/loginpage
				.antMatchers("/loginpage").permitAll()
					//權限判斷
				//必須要也admin 權限可以訪問
				.antMatchers("/adminpage").hasAnyAuthority("admin")
				//必須要有manager 角色才可以訪問
				.antMatchers("/managerpage").hasRole("manager")
				//其他任意角色都可以訪問
				.antMatchers("/employeepage").hasAnyRole("manager","employee")
				//其他的都要被認證
				.anyRequest().authenticated();
				
				//登出
				http.logout()
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/loginpage")
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
				//異常處理
				http.exceptionHandling()
//					.accessDeniedPage("/異常處理頁面")
					.accessDeniedHandler(myAccessDeniedHandler);
				//勿忘我(remember-me)
				http.rememberMe()
					.userDetailsService(userDetailsService)
					.tokenValiditySeconds(60*60*24);
			}

			@Bean
			public PasswordEncoder getpasswordEncoder() {
				return new BCryptPasswordEncoder();
			}
}
