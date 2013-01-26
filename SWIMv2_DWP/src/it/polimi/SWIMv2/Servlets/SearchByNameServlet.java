package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
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
 * Servlet implementation class SearchByNameServlet
 */
public class SearchByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private InitialContext ctx;
	
	private SearchBeanLocal sb;
	
	FriendshipBeanLocal fb;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchByNameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("nome");
		String lastName = request.getParameter("cognome");
		
		try {
			ctx = new InitialContext();
			sb = (SearchBeanLocal)ctx.lookup("SearchBean/local");
			fb = (FriendshipBeanLocal)ctx.lookup("FriendshipBean/local");
			
			List<String> results = (List<String>)sb.searchByName(firstName, lastName);
			
			if(!results.isEmpty()){
				List<String> resultsHTML = new ArrayList<String>();
				for (String result : results) {
					String userEmail1 = request.getSession().getAttribute("email").toString();
					String userEmail2 = result.split("\t")[2];
					if (!userEmail1.equals(userEmail2)) {
						if (fb.areAlreadyFriends(userEmail1, userEmail2)) {
							if(!fb.isUnconfirmedFriendship(userEmail1, userEmail2)) {
								result += "\t" + "2";
							}
							else if (fb.isUnconfirmedFriendship(userEmail1, userEmail2)) {
								result += "\t" + "1";
							}
						}
						else {
							result += "\t" + "0";
						}
						resultsHTML.add(result);
					}
					else {
						request.getSession().setAttribute("alert", "L'unico risultato trovato sei tu. (:");
						getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
					}
				}
				request.getSession().setAttribute("resultslist", resultsHTML);
				getServletConfig().getServletContext().getRequestDispatcher("/searchresult.jsp").forward(request, response);
			}
			else {
				request.getSession().setAttribute("alert", "Nessun risultato trovato.");
				getServletConfig().getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
			}		
		} catch (NamingException e) {
			e.printStackTrace();
		} catch(Exception e){
			System.out.println("La ricerca non ha prodotto risultati, si prega di riprovare");
		}
		
	}

}
