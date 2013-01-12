package it.polimi.SWIMv2.Utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class ActivationByMail {
	
	//NON FUNZIONA ANCORA!!!
	public void sendMail (String dest, String mitt, String oggetto, String testoEmail)
		      throws MessagingException
		  {
		    // Creazione di una mail session
		    Properties props = new Properties();
		    props.put("mail.smtp.host", "localhost");
		    Session session = Session.getInstance(props);
		    

		    // Creazione del messaggio da inviare
		    MimeMessage message = new MimeMessage(session);
		    message.setSubject(oggetto);
		    message.setText(testoEmail);

		    // Aggiunta degli indirizzi del mittente e del destinatario
		    InternetAddress fromAddress = new InternetAddress(mitt);
		    InternetAddress toAddress = new InternetAddress(dest);
		    message.setFrom(fromAddress);
		    message.setRecipient(Message.RecipientType.TO, toAddress);

		    // Invio del messaggio
		    Transport.send(message);
		  }
}
