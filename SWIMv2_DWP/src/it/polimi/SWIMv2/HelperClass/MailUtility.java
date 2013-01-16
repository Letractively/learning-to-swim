package it.polimi.SWIMv2.HelperClass;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class MailUtility
{
  public static void sendMail (String receiver, String sender, String body)
      throws MessagingException
  {
    // Creazione di una mail session
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.mioprovider.it");
    Session session = Session.getDefaultInstance(props);

    // Creazione del messaggio da inviare
    MimeMessage message = new MimeMessage(session);
    message.setContent("<h2>" + body + " HTML</h2>", "text/html");

    // Aggiunta degli indirizzi del mittente e del destinatario
    InternetAddress fromAddress = new InternetAddress(sender);
    InternetAddress toAddress = new InternetAddress(receiver);
    message.setFrom(fromAddress);
    message.setRecipient(Message.RecipientType.TO, toAddress);

    // Invio del messaggio
    Transport.send(message);
  }

}