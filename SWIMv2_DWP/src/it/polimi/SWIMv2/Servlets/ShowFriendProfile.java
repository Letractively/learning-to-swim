package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.FriendProfileSessionBeanLocal;
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
	private FriendProfileSessionBeanLocal friendProfile;
	private FriendshipBeanLocal fb;
       
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
		    friendProfile = (FriendProfileSessionBeanLocal)ctx.lookup("FriendProfileSessionBean/local");
		    fb = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
		    
		    String friendEmail = (String)request.getParameter("email").toString();
		    String friend = friendProfile.getFriendData(friendEmail);
            request.getSession().setAttribute("userData", friend);
            
            String userMail = request.getSession().getAttribute("email").toString();
            if (fb.areDirectFriends(userMail, friendEmail)) {
            	List<String> hypoFriends = (List<String>)fb.getHypoteticalIndirectFriends(userMail, friendEmail);
            	request.getSession().setAttribute("hypoFriends", hypoFriends);
            }
            
            getServletConfig().getServletContext().getRequestDispatcher("/friendprofile.jsp").forward(request, response);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
		catch (NullPointerException e){
			getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
