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
 * Servlet implementation class FriendshipRequestServlet
 */
public class FriendshipRequestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       

	private InitialContext ctx;
	private FriendshipBeanLocal friendshipBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ctx = new InitialContext();
		    friendshipBean = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
		    
		    String userEmail1 = (String)request.getSession().getAttribute("email");

			boolean direct = false;
			String userEmail2 = "";
			if (request.getParameter("friendEmail") != null && !request.getParameter("friendEmail").isEmpty()) {
				direct = false;
				userEmail2 = request.getParameter("friendEmail");
			}
			else if (request.getParameter("directFriendEmail") != null && !request.getParameter("directFriendEmail").isEmpty()) {
				direct = true;
				userEmail2 = request.getParameter("directFriendEmail");
			}
		    
		    friendshipBean.friendshipRequest(userEmail1, userEmail2, direct);
		    getServletConfig().getServletContext().getRequestDispatcher("/friends").forward(request, response);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
