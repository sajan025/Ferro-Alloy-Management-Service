package com.jsp.ferro.alloy;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


/**
 * @author Sajan
 *
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "FERRO-ALLOY API", version = "2.0", description = "FERRO-ALLOY Information"))
public class FerroAlloyApplication {
	//sajan
	public static void main(String[] args) {
		SpringApplication.run(FerroAlloyApplication.class, args);
	}
	
	@Value("${spring.mail.host}")
    private String host;

    @Value("${spring.mail.port}")
    private int port;
	@Bean
	public JavaMailSender MailSender() {
		 JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
	        javaMailSender.setHost(host);
	        javaMailSender.setPort(port);
	        //javaMailSender.setUsername(username);
	        //javaMailSender.setPassword(password);
	        return javaMailSender;
	}
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
