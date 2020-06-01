package io.badri.app.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import io.badri.app.entity.Mail;
import io.badri.app.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Async
	public void sendMail(Mail mail,String token) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		try {
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setText(mail.getMailContent());
			mimeMessageHelper.setFrom(mail.getMailFrom());
			mimeMessageHelper.setTo(mail.getMailTo());
			

			String name = mail.getMailTo().split("@")[0];

//			mimeMessage.setContent("<html>" + "<head>" + "<h1>Hello&nbsp;" + name + "</h1>" + "</head>" + "<body>"
//					+ "<p>" + "Please click the button below" + "or use the following url to activate account."  + "</p>"
//					+  "<a href='http://localhost:8080/email-verified?token="+ UUID.randomUUID() +"'" +">"+ "Activate </a>"  + "<p>" + "With Regards" + "<br>"
//					+ "Hamro Spring boot." + "</p>" + "</body>" + "</html>" + mail.getMailTo(), "text/html");

			
			mimeMessage.setContent("<html>" + "<head>" + "<h1>Hello&nbsp;" + name + "</h1>" + "</head>" + "<body>"
					+ "<p>" + "Please click the button below" + "or use the following url to activate account."  + "</p>"
					+  "<a href='http://localhost:8080/signup/emailverification/"+ token +"'" +">"+ "Activate Account </a>"  + "<p>" + "With Regards" + "<br>"
					+ "Hamro Spring boot." + "</p>" + "</body>" + "</html>" + mail.getMailTo(), "text/html");

		        	javaMailSender.send(mimeMessage);
	
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void sendMailWithAttachment(Mail mail) throws MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		try {
			mimeMessageHelper.setSubject(mail.getMailSubject());
			mimeMessageHelper.setText(mail.getMailContent());
			mimeMessageHelper.setFrom(mail.getMailFrom());
			mimeMessageHelper.setTo(mail.getMailTo());

			FileSystemResource file = new FileSystemResource(
					"C:\\Users\\Badri Paudel\\Desktop\\Footage_Amaz_Wrld\\kathmandu_images\\basantaput.jpg");
			mimeMessageHelper.addAttachment(file.getFilename(), file);

			javaMailSender.send(mimeMessage);
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

}
