package it.polimi.SWIMv2.HelperClass;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailUtility{
  
	
	public static void sendMail (String receiver, String sender, String body) throws MessagingException{
	  
		final String username = "learningtoswimv2@gmail.com";
		final String password = "daveisafuckingnerd";
	   
	    Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
	    
	    MimeMessage message = setMessage(session, sender, receiver, body);
	    Transport.send(message);
  
    }

	private static MimeMessage setMessage(Session session, String sender, String receiver, String body) throws MessagingException{
	
	    MimeMessage message = new MimeMessage(session);
	    message.setContent("<h2>" + body + "</h2>", "text/html");
	    
	    InternetAddress fromAddress = new InternetAddress(sender);
	    InternetAddress toAddress = new InternetAddress(receiver);
	    message.setFrom(fromAddress);
	    message.setRecipient(Message.RecipientType.TO, toAddress);
	    message.setSubject(sender + " ti ha inviato un messaggio su SWIMv2");
	    return message;
  
	}

}