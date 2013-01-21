package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.FriendshipBeanLocal;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FriendshipConfirmationServlet
 */
public class FriendshipConfirmationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
 
	private InitialContext ctx;
	private FriendshipBeanLocal friendshipBean;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		try {
			
			ctx = new InitialContext();
		    friendshipBean = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
		
		    Long userId1 = (Long)request.getSession().getAttribute("id");
			Long userId2 = Long.parseLong(request.getParameter("friendId"));
		   
		    // sfriendshipBean.confirmFriendship(userId1, userId2);
		    
		    } 
		catch (NamingException e) {
			e.printStackTrace();
		    }
	   
		
	}


}
