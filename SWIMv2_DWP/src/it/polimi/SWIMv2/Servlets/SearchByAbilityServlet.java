package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.SessionBeans.AbilityBeanLocal;
import it.polimi.SWIMv2.SessionBeans.FriendshipBeanLocal;
import it.polimi.SWIMv2.SessionBeans.SearchBeanLocal;

import java.io.IOException;
import java.util.ArrayList;
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
	private FriendshipBeanLocal fb;
	
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
	
		//TODO RUFY RIADATTA QUESTA RIGA
		Long ability = new Long(request.getParameter("ability"));
		
		try {
			ctx = new InitialContext();
			sb = (SearchBeanLocal)ctx.lookup("SearchBean/local");
			fb = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
			
			List<String> results = (List<String>)sb.searchByAbility(ability);
			
			if(!results.isEmpty()){
				List<String> resultsHTML = new ArrayList<String>();
				for (String result : results) {
					String userEmail1 = request.getSession().getAttribute("email").toString();
					String userEmail2 = result.split("\t")[2];
					if (fb.areAlreadyFriends(userEmail1, userEmail2)) {
						if(fb.isUnconfirmedFriendship(userEmail1, userEmail2)) {
							result += "\t" + "2";
						}
						else if (!fb.isUnconfirmedFriendship(userEmail1, userEmail2)) {
							result += "\t" + "1";
						}
					}
					else {
						result += "\t" + "0";
					}
					resultsHTML.add(result);
				}
				request.getSession().setAttribute("resultslist", resultsHTML);
				getServletConfig().getServletContext().getRequestDispatcher("/searchresult.jsp").forward(request, response);
			}
			else {
				request.getSession().setAttribute("alert", "Nessun risultato trovato.");
				getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("La ricerca non ha prodotto risultati, si prega di riprovare");
		}
		
	}

}
