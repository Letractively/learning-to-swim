package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.FriendshipBeanLocal;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowFriendProfile
 */
public class ShowFriendProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InitialContext ctx;
	private FriendshipBeanLocal friendshipBean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowFriendProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			ctx = new InitialContext();
		    friendshipBean = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
		    
            /*String userEmail = (String)request.getSession().getAttribute("email");
		
            List<String> friends = (List<String>)friendshipBean.getAllFriends(userEmail);
           
            request.getSession().setAttribute("friends", friends);
			
    	    for(String friend: friends){
    	    	System.out.println(friend);
    	    }*/
            
            getServletConfig().getServletContext().getRequestDispatcher("/friendprofile.jsp").forward(request, response);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
