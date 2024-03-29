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
			AbilityBeanLocal abilityBean = (AbilityBeanLocal)ctx.lookup("AbilityBean/local");
			
			String userEmail = request.getSession().getAttribute("email").toString();
			request.getSession().setAttribute("userabilities", abilityBean.returnUserAbilities(userEmail));
			request.getSession().setAttribute("addabilities", abilityBean.returnAbilityToAdd(userEmail));
			
			getServletConfig().getServletContext().getRequestDispatcher("/ability.jsp").forward(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
