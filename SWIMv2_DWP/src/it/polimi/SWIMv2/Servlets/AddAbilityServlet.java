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
 * Servlet implementation class AddAbilityServlet
 */
public class AddAbilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	private InitialContext ctx;
	private AbilityBeanLocal abilityBean;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			ctx = new InitialContext();
		    abilityBean = (AbilityBeanLocal)ctx.lookup("AbilityBean/local");
		
		    String[] ability = request.getParameterValues("ability") ;
            String userEmail = request.getSession().getAttribute("email").toString();
            
            if (ability != null) {
	            //abilityBean.deleteAllUserAbilities(userEmail);
	            for(String s : ability ){
	            	System.out.println(s + "\n");
	            	Long idAbility = Long.parseLong(s);
	    	        abilityBean.addAbilityToUser(userEmail, idAbility);
	            }
			
	            request.getSession().setAttribute("alert", "Abilita' aggiunta!");
            }
			getServletConfig().getServletContext().getRequestDispatcher("/ability").forward(request, response);
		} 
		catch (NamingException e) {
			e.printStackTrace();
		}
	}


}

