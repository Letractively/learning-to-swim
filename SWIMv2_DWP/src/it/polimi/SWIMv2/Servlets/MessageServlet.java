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
		
		String senderEmail = (String)request.getSession().getAttribute("email");
		String receiverEmail = request.getParameter("receiverEmail");
		String body = request.getParameter("body");
		
		try {
			ctx = new InitialContext();
			messageBean = (MessageBeanLocal)ctx.lookup("MessageBean/local");
			
			if(messageBean.validReceiver(request.getParameter("receiverEmail"))){
				messageBean.insertToDatabase(senderEmail, receiverEmail, body);
				boolean delivered = forwarding(receiverEmail, senderEmail, body);
				
				if(delivered){
					request.getSession().setAttribute("messagedelivered", "Invio riuscito!");
					getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				}
				
				else{
					request.getSession().setAttribute("errormessage", "Invio fallito: better luck next time!");
					getServletConfig().getServletContext().getRequestDispatcher("/messageForm.jsp").forward(request, response);
				}
			}
			else{
				request.getSession().setAttribute("errormessage", "Invio fallito: better luck next time!");
				getServletConfig().getServletContext().getRequestDispatcher("/messageForm.jsp").forward(request, response);
			}
		} 
		catch (NamingException e) {
			e.printStackTrace();
	    } 

	}
	
	private boolean forwarding(String receiver, String sender, String body){
		
		try {
			 MailUtility.sendMail(receiver, sender, body);
			 System.out.println("Invio messaggio OK!");
			 return true;
		} 
		catch (MessagingException e) {
			
			System.out.println("Invio non riuscito! Porta 25 chiusa!!");
		    return false;
		}
	
	}

	
}
