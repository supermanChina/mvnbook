package com.junvenxu.mvnbook.account.email;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext ctx = new ClassPathXmlApplicationContext("account-email.xml");
        PropertyPlaceholderConfigurer config = (PropertyPlaceholderConfigurer) ctx.getBean("propertyConfigurer");
        System.out.println("Application context load beans completed");
    }
}
