package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.Admin;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.Exceptions.AlreadyValidatedUserException;
import it.polimi.SWIMv2.SessionBeans.ActivationBeanLocal;
import it.polimi.SWIMv2.SessionBeans.LoginBeanLocal;
import it.polimi.SWIMv2.SessionBeans.FriendProfileSessionBeanLocal;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ConfirmationServlet
 */
public class ConfirmationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InitialContext ctx;
	private ActivationBeanLocal ab;
	private FriendProfileSessionBeanLocal usbl;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(avoidIncorrectAccess(request, response)){
			doNormalOperations(request, response);
		}
		else{
			//response.sendRedirect("index.jsp");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	private void doNormalOperations(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			ctx = new InitialContext();
			ab = (ActivationBeanLocal)ctx.lookup("ActivationBean/local");
			
			String confirmationCode = request.getParameter("activationcode");
			String email = request.getParameter("user");
			System.out.println(email + ", " + confirmationCode);
			GenericUser u = ab.activateAccount(email, confirmationCode);
			
			if(u != null){
				request.getSession().setAttribute("alertconfirmed", "Il tuo account è stato confermato, ora puoi effettuare il login");
				getServletConfig().getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
			
		}catch(NamingException e){
			System.out.println("Attivazione non riuscita");
		}catch(AlreadyValidatedUserException e){
			System.out.println("Utente già validato: eseguo redirect alla homepage");
			response.sendRedirect("index.jsp");
		}catch(Exception e){
			System.out.println("Eccezione nella conferma email");
			response.sendRedirect("index.jsp");
		}
		
	}

	private boolean avoidIncorrectAccess(HttpServletRequest request, HttpServletResponse response) {
		try{
			String activationCode = (String)request.getParameter("activationcode");
			if(activationCode == null){
				return false;
			}
			return true;
		}
		catch(Exception e){
			System.out.println("Sono qui");
			return false;
		}
		
	}

}
