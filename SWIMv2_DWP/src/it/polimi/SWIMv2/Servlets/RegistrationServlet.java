package it.polimi.SWIMv2.Servlets;


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
		System.out.println(request.getParameter("nome")+request.getParameter("nome")+request.getParameter("email")+request.getParameter("password")+request.getParameter("citta'"));
	
		try {
			ctx = new InitialContext();
			rb = (RegistrationBeanLocal)ctx.lookup("RegistrationBean/local");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		rb.insertIntoDatabase(request.getParameter("nome"), request.getParameter("nome"), request.getParameter("email"), request.getParameter("password"), request.getParameter("citta'"));
		
	}
	
	
	

}
