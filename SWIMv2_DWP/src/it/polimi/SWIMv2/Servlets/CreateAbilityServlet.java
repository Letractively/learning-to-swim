package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.AbilityBeanLocal;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateAbilityServlet
 */
public class CreateAbilityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	private InitialContext ctx;
	private AbilityBeanLocal abilityBean;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			ctx = new InitialContext();
		    abilityBean = (AbilityBeanLocal)ctx.lookup("AbilityBean/local");
		
		    String abilityName = request.getParameter("abilityName") ;
            String abilityDescription = request.getParameter("abilityDescription");
            String creatorEmail = (String)request.getSession().getAttribute("email");
            
            abilityBean.createAbility(abilityName, abilityDescription, creatorEmail);
		
            request.getSession().setAttribute("abilitycreated", "Abilita' creata con successo!");
			getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
	    
            } 
		    
		catch (NamingException e) {
			e.printStackTrace();
		    }

	}


}
