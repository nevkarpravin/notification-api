package com.sungardas.service;

import com.sungardas.model.Mail;

public interface MailService {
	public void sendEmail(Mail mail);
	public void sendMailUsingJMail(Mail mail);
}
