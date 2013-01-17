package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.Admin;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.Exceptions.AlreadyValidatedUserException;
import it.polimi.SWIMv2.SessionBeans.ActivationBeanLocal;
import it.polimi.SWIMv2.SessionBeans.LoginBeanLocal;
import it.polimi.SWIMv2.SessionBeans.UserSessionBeanLocal;

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
	private UserSessionBeanLocal usbl;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
			/*if(u != null){
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
				request.getSession().setAttribute("confirmed", true);
				
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
			}*/
			
		}catch(NamingException e){
			System.out.println("Attivazione non riuscita");
			e.printStackTrace();
		}catch(AlreadyValidatedUserException e){
			System.out.println("Utente già validato: eseguo redirect alla homepage");
			response.sendRedirect("index.jsp");
		}
		
	}

}
