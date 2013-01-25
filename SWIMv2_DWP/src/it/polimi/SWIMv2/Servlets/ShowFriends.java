package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.SessionBeans.AbilityBeanLocal;
import it.polimi.SWIMv2.SessionBeans.FriendshipBeanLocal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowFriends
 */
public class ShowFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private InitialContext ctx;
	private FriendshipBeanLocal friendshipBean;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ctx = new InitialContext();
		    friendshipBean = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
		    
            String userEmail = (String)request.getSession().getAttribute("email");
		
            List<String> friends = friendshipBean.getAllFriends(userEmail);
            List<String> friendsHTML = new ArrayList<String>();
			
    	    for(String friend : friends){
    	    	String[] friendDetails = friend.split(" ");
    	    	
    	    	String email = friendDetails[friendDetails.length - 2];
    	    	Boolean confirmed = friendDetails[friendDetails.length - 1].equals("true");
    	    	
    	    	String nomeCognome = "";
    	    	for (int i = 0; i < friendDetails.length - 2; i++) {
    	    		String detail = friendDetails[i];
    	    		nomeCognome += detail + " ";
    	    	}
    	    	nomeCognome = nomeCognome.subSequence(0, nomeCognome.length() - 1).toString();
    	    	
    	    	if (confirmed) {
    	    		friendsHTML.add("<a href='friendprofile?email=" + email  + "'>" + nomeCognome + "</a>");
    	    	}
    	    	else {
    	    		friendsHTML.add(nomeCognome + " <button>Conferma amicizia</button>");
    	    	}
    	    }
    	    
    	    request.getSession().setAttribute("friends", friendsHTML);            
            getServletConfig().getServletContext().getRequestDispatcher("/friends.jsp").forward(request, response);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
