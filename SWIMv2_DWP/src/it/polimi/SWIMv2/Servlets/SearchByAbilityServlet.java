package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.SessionBeans.SearchBeanLocal;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchByAbilityServlet
 */
public class SearchByAbilityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private InitialContext ctx;
	
	private SearchBeanLocal sb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByAbilityServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ability = request.getParameter("ability");
		
		try {
			ctx = new InitialContext();
			sb = (SearchBeanLocal)ctx.lookup("SearchBean/local");
			
			List<GenericUser> results = (List<GenericUser>)sb.searchByAbility(ability);
			
			if(results.isEmpty()){
				System.out.println("Non ci sono utenti con tale abilità");
			}
			else{
				System.out.println("Il primo risultato è " + ((GenericUser)results.get(0)).getEmail());
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
