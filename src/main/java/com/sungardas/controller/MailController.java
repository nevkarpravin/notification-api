package com.sungardas.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sungardas.model.Mail;
import com.sungardas.service.MailService;

@RestController
public class MailController {
	
	@Autowired
	 MailService mailService;
	 
	@RequestMapping("/getMail")
	public ResponseEntity<String> triggerMail() throws AddressException, MessagingException, IOException
	{
		Mail mail = new Mail();
        mail.setMailFrom("abcd@abcd.com");
        mail.setMailTo("nevkarpravin@gmail.com");
        mail.setMailSubject("Email Notification Example");
        mail.setMailContent("This is sample mail");
        mail.setAttachments(new ClassPathResource("sampleFile.txt"));
        mailService.sendEmail(mail);
      // mailService.sendMailUsingJMail(mail);
return ResponseEntity.ok("Email SentSuccessfully");
		
	}
	
}
