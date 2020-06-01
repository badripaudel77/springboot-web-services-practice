package io.badri.app.rest;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.badri.app.entity.Mail;
import io.badri.app.service.impl.MailServiceImpl;

@RestController
public class MailController {

	
	@Autowired
	private MailServiceImpl mailService;
	
//	@RequestMapping(value = "/sendemail")
//	public String sendMail() throws MessagingException  {
//		Mail mail = new Mail();
//		mail.setMailFrom("spring.navigate@gmail.com");
//		mail.setMailTo("badripaudel77@gmail.com");
//		mail.setMailSubject("Spring Boot - Email Service");
//		mail.setMailContent("Learn how to send email using Spring Boot!");
//		mailService.sendMail(mail);
//		return "Mail Sent!";
//	}
	
	@RequestMapping(value = "/sendemailwithattachment")
	@ResponseBody
	public String sendMailAttachment() throws MessagingException {
		Mail mail = new Mail();
		mail.setMailFrom("spring.navigate@gmail.com");
		mail.setMailTo("badri@ncit.edu.np");
		mail.setMailSubject("Spring Boot - Email Service");
		mail.setMailContent("Email verification");
		mailService.sendMailWithAttachment(mail);
		return "Mail Sent!"; // return in this format {"msg" : "mail sent"}
	}
		
	@RequestMapping("/email-verified")
	@ResponseBody
	     public String emailVerified() {
		
		return "Account has been activated successfully ";
	}
	
	
	
}
