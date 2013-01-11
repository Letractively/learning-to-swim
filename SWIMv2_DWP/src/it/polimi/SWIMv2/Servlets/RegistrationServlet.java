package it.polimi.SWIMv2.Servlets;


import it.polimi.SWIMv2.Exceptions.IllegalEmailException;
import it.polimi.SWIMv2.SessionBeans.RegistrationBean;
import it.polimi.SWIMv2.SessionBeans.RegistrationBeanLocal;

import java.io.IOException;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class RegistrationServlet
 */
public class RegistrationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private InitialContext ctx;
	
	//@EJB
	private RegistrationBeanLocal rb;
	
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO rimuovere la println
		//System.out.println(request.getParameter("nome")+request.getParameter("cognome")+request.getParameter("email")+request.getParameter("password")+request.getParameter("citta'"));
	
		try {
			ctx = new InitialContext();
			rb = (RegistrationBeanLocal)ctx.lookup("RegistrationBean/local");
			rb.insertIntoDatabase(request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("email"), request.getParameter("password"), request.getParameter("citta'"));
			//TODO eseguire le azioni successive
		} catch (NamingException e) {
			// TODO rimuovere la println
			System.out.println("qualcosa non va nella registrazione");
			e.printStackTrace();
		} catch (IllegalEmailException e){
			// TODO rimuovere la println
			System.out.println("email malformata");
		}
		
		
		
	}
	
	
	

}
