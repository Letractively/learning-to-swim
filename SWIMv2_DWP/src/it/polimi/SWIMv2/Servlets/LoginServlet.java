package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.Admin;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.SessionBeans.LoginBeanLocal;
import it.polimi.SWIMv2.SessionBeans.UserSessionBeanLocal;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private InitialContext ctx;
	private LoginBeanLocal lb;
	private UserSessionBeanLocal usbl;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("email")+", " + request.getParameter("password"));
		
		try {
			ctx = new InitialContext();
			lb = (LoginBeanLocal)ctx.lookup("LoginBean/local");
			GenericUser u = lb.validateUser(request.getParameter("email"), request.getParameter("password"));
			//TODO rimuovere la println
			if(u != null){
				System.out.println("login corretto");
				usbl = (UserSessionBeanLocal)ctx.lookup("UserSessionBean/local");
				//GenericUser u = usbl.getDataFromDatabase(request.getParameter("email"));
				request.getSession().setAttribute("nome", u.getFirstName());
				request.getSession().setAttribute("cognome", u.getLastName());
				request.getSession().setAttribute("city", u.getCity());
				request.getSession().setAttribute("email", u.getEmail());
				request.getSession().setAttribute("feedback", u.getFeedback());
				request.getSession().setAttribute("contesto", usbl);
				request.getSession().setAttribute("logged", true);
				
				//Object userClass = u.getClass();
				
				if(u.getClass().equals(User.class)){
					request.getSession().setAttribute("type", "utente normale");
				}
				else if(u.getClass().equals(Admin.class)){
					request.getSession().setAttribute("type", "amministratore");
				}
				
				//response.sendRedirect("profile.jsp");
				getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
				
				System.out.println("Redirect alla pagina personale completato con successo");
			}
			else{
				System.out.println("login non corretto");
			}
			//TODO eseguire le azioni successive
		} catch (NamingException e) {
			// TODO rimuovere la println
			System.out.println("qualcosa non va nel login");
			e.printStackTrace();
		}
		
	}

}
