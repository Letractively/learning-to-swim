package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.LoginBeanLocal;
import it.polimi.SWIMv2.SessionBeans.RegistrationBeanLocal;

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
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("email")+", " + request.getParameter("password"));
		
		try {
			ctx = new InitialContext();
			lb = (LoginBeanLocal)ctx.lookup("LoginBean/local");
			lb.validateUser(request.getParameter("email"), request.getParameter("password"));
			//TODO eseguire le azioni successive
		} catch (NamingException e) {
			// TODO rimuovere la println
			System.out.println("qualcosa non va nel login");
			e.printStackTrace();
		}
	}

}
