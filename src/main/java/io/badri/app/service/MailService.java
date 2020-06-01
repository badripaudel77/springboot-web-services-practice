package io.badri.app.service;

import javax.mail.MessagingException;

import io.badri.app.entity.Mail;

public interface MailService {
	
	public void sendMail(Mail mail,String token) throws MessagingException;
	public void sendMailWithAttachment(Mail mail) throws MessagingException;
}
