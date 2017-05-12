/**
 * 
 */
package com.junvenxu.mvnbook.account.email;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.mail.internet.MimeMessage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;

/**
 * @author Xiaochao.Zhang
 *
 */
public class AccountEmailServiceTest {

	private GreenMail greenMail;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		greenMail = new GreenMail(ServerSetup.SMTP);
		greenMail.setUser("user@163.com", "password");
		greenMail.start();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		greenMail.stop();
	}

	/**
	 * Test method for
	 * {@link com.junvenxu.mvnbook.account.email.AccountEmailServiceImpl#sendMail(java.lang.String, java.lang.String, java.lang.String)}.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSendMail() throws Exception {
		ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"account-email.xml");
		AccountEmailService accountEmailService = (AccountEmailService) classPathXmlApplicationContext
				.getBean("accountEmailService");

		String subject = "Test Subject";
		String htmlText = "<h3>Test</h3>";

		accountEmailService.sendMail("user@163.com", subject, htmlText);

		boolean isOk = greenMail.waitForIncomingEmail(2000, 1);
		assertTrue(isOk);

		MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
		assertEquals(1, receivedMessages.length);
		assertEquals(subject, receivedMessages[0].getSubject());
		assertEquals(htmlText, GreenMailUtil.getBody(receivedMessages[0]).trim());
	}

}
