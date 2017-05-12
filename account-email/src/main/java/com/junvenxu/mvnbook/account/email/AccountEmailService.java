/**
 * 
 */
package com.junvenxu.mvnbook.account.email;

/**
 * @author Xiaochao.Zhang
 *
 */
public interface AccountEmailService {
	void sendMail(String to, String subject, String htmlText) throws AccountEmailException;
}
