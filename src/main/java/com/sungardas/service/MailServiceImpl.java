package com.sungardas.service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sungardas.model.Mail;

@Service("mailService")
public class MailServiceImpl implements MailService {
 
    @Autowired
    JavaMailSender mailSender;
    @Autowired
    Session session;
    public void sendEmail(Mail mail) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
 
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
 
            mimeMessageHelper.setSubject(mail.getMailSubject());
            mimeMessageHelper.setFrom(new InternetAddress(mail.getMailFrom()));
            mimeMessageHelper.setTo(mail.getMailTo());
            mimeMessageHelper.setText(mail.getMailContent());
           
            mimeMessageHelper.addAttachment( "sampleAttachment",  mail.getAttachments());
            mailSender.send(mimeMessageHelper.getMimeMessage());
           
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public void sendMailUsingJMail(Mail mail) {
    	try {
    	MimeMessage msg = new MimeMessage(session);
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	     msg.addHeader("format", "flowed");
	     msg.addHeader("Content-Transfer-Encoding", "8bit");
	      
	     msg.setFrom(new InternetAddress("no_reply@example.com", "NoReply-JD"));

	     msg.setReplyTo(InternetAddress.parse("no_reply@example.com", false));

	     msg.setSubject(mail.getMailSubject());

	     msg.setSentDate(new Date());

	     msg.setRecipients(Message.RecipientType.TO, mail.getMailTo());
	      
        // Create the message body part
        BodyPart messageBodyPart = new MimeBodyPart();

        // Fill the message
        messageBodyPart.setText(mail.getMailContent());
        
        // Create a multipart message for attachment
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Second part is attachment
			/*
			 * messageBodyPart = new MimeBodyPart(); String filename = "abc.txt"; DataSource
			 * source = new FileDataSource(filename);
			 * messageBodyPart.setDataHandler(mail.getAttachments());
			 * messageBodyPart.setFileName(filename);
			 * multipart.addBodyPart(messageBodyPart);
			 */

        // Send the complete message parts
        msg.setContent(multipart);

        // Send message
         Transport transport =session.getTransport();
        transport.send(msg );
        System.out.println("EMail Sent Successfully with attachment!!");
     }catch (MessagingException e) {
        e.printStackTrace();
     } catch (UnsupportedEncodingException e) {
		 e.printStackTrace();
	}

    }
}