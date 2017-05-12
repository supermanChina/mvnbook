/**
 * 
 */
package com.junvenxu.mvnbook.account.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * @author Xiaochao.Zhang
 *
 */
public class AccountEmailServiceImpl implements AccountEmailService {

	private JavaMailSender javaMailSender;

	private String systemEmail;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.junvenxu.mvnbook.account.email.AccountEmailService#sendMail(java.lang
	 * .String, java.lang.String, java.lang.String)
	 */
	public void sendMail(String to, String subject, String htmlText) throws AccountEmailException {
		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper msgHelper = new MimeMessageHelper(msg);

		try {
			msgHelper.setFrom(systemEmail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);
		} catch (MessagingException e) {
			throw new AccountEmailException("Fail to send mail.", e);
		}
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public String getSystemEmail() {
		return systemEmail;
	}

	public void setSystemEmail(String systemEmail) {
		this.systemEmail = systemEmail;
	}

}
