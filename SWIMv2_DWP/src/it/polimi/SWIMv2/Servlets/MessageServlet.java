package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.HelperClass.MailUtility;
import it.polimi.SWIMv2.SessionBeans.MessageBeanLocal;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MessageServlet
 */
public class MessageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
        
	private InitialContext ctx;
	private MessageBeanLocal messageBean;

		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String senderEmail = request.getParameter("senderEmail");
		String receiverEmail = request.getParameter("receiverEmail");
		String body = request.getParameter("body");
		
		try {
			ctx = new InitialContext();
			messageBean = (MessageBeanLocal)ctx.lookup("MessageBean/local");
			
			if(messageBean.validReceiver(request.getParameter("receiverEmail"))){
				messageBean.insertToDatabase(senderEmail, receiverEmail, body);
				forwarding(receiverEmail, senderEmail, body);
			}
			
		} 
		catch (NamingException e) {
			e.printStackTrace();
	    } 

	}
	
	private void forwarding(String receiver, String sender, String body){
		
		try {
			 MailUtility.sendMail(receiver, sender, body);
			 System.out.println("Invio messaggio OK!");
		} 
		catch (MessagingException e) {
			
			System.out.println("Invio non riuscito!");
		    e.printStackTrace();
		}
	}

	
}
