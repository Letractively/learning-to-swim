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
    	    	String[] friendDetails = friend.split("\t");
    	    	
    	    	String nome = friendDetails[0];
    	    	String cognome = friendDetails[1];
    	    	String email = friendDetails[2];
    	    	String type = friendDetails[3];
    	    	
    	    	if (type.equals("1")) {
    	    		friendsHTML.add(nome + " " + cognome + " <button class='likeConfirm' type='submit' name='friendship' value='" + email + "'>Conferma</button>");
    	    	}
    	    	else if (type.equals("2")) {
    	    		friendsHTML.add("<a href='friendprofile?email=" + email  + "'>" + nome + " " + cognome + "</a>");
    	    	}
    	    	else {
    	    		friendsHTML.add(nome + " " + cognome + " - Amicizia in attesa di conferma");
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
