package com.sungardas.configuration;

import java.util.Properties;

import javax.mail.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfiguration {
 
    @Autowired
    private Environment env;
 
    @Bean
    public JavaMailSender getMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
 
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.valueOf(env.getProperty("spring.mail.port")));
        //mailSender.setUsername(env.getProperty("spring.mail.username"));
        //mailSender.setPassword(env.getProperty("spring.mail.password"));
        
        Properties javaMailProperties = new Properties();
        //javaMailProperties.put("mail.smtp.starttls.enable", "true");
        //javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
 
        mailSender.setJavaMailProperties(javaMailProperties);
        return mailSender;
    }
    
    @Bean
    public Session getSession() {
    	Properties javaMailProperties = new Properties();
       // javaMailProperties.put("mail.smtp.starttls.enable", "true");
        //javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.host", env.getProperty("spring.mail.host"));
        javaMailProperties.put("mail.smtp.host", Integer.valueOf(env.getProperty("spring.mail.port")));
        javaMailProperties.put("mail.store.protocol","pop3");
    	Session session = Session.getDefaultInstance(javaMailProperties);
    	return session;
    	
    }
    
}