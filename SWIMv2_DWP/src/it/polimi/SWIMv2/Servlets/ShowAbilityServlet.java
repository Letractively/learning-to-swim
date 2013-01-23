package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.Ability;
import it.polimi.SWIMv2.SessionBeans.AbilityBeanLocal;

import java.io.IOException;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AbilityServlet
 */
public class ShowAbilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowAbilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			System.out.println("AAA");
			AbilityBeanLocal abilityBean = (AbilityBeanLocal)ctx.lookup("AbilityBean/local");
			
			Map<Ability,Boolean> abs = abilityBean.getAbilitiesByUser("gabri.ruflex@gmail.com");
			request.setAttribute("abilities", abs);
			System.out.println(abs.isEmpty());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}
