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
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
			request.getSession().setAttribute("totalabilities", abilityBean.returnTotalAbilityList());
			
			getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
