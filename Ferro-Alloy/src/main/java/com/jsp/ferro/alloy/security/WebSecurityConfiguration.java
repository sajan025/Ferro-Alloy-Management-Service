package com.jsp.ferro.alloy.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.jsp.ferro.alloy.security.JwtTokenFilter;
import com.jsp.ferro.alloy.security.JwtTokenFilterConfigurer;
import com.jsp.ferro.alloy.security.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

/**
 * @author Sajan Yadav
 * @Date 15 Sept 2023
 * 
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	 private final JwtTokenProvider jwtTokenProvider;
   
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	http.cors().and().csrf().disable().authorizeRequests().antMatchers("/","/actuator/**","/login/**","/css/**","/js/**","/angular/**","/resources/**", "/static/**", "/public/**").permitAll()
	    	.antMatchers("/sms","/smsHistory/**","/costingHistory/**","/financeHistory/**","/qualityHistory/**","/quality","/finance","/admin","/master-grades","/master-Internal-grades","/master-Chemistry","/export-to-excel/**").permitAll()
	    	.antMatchers("/auth/signin/**").permitAll()
	    	.antMatchers("/auth/refresh-token/**").permitAll()
	    	.antMatchers("/auth/**","/swagger-ui/**","/swagger-ui.html","/v3/api-docs/**")
			.permitAll().anyRequest().authenticated().and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    	http.exceptionHandling().accessDeniedPage("/login");
	        http.apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

	    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
   

}
