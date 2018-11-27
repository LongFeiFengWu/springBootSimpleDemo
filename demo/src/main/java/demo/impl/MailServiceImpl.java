package demo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import demo.config.SMSConfig;

@Service(value = "mailService")
public class MailServiceImpl {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private SMSConfig smsConfig;

	public void sendSimpleMail(String des, String verCode) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(smsConfig.getMailFrom());
		message.setTo(des);
		message.setSubject(smsConfig.getSubject());
		message.setText(smsConfig.getContent().replace("*", verCode));

		mailSender.send(message);
	}

}
