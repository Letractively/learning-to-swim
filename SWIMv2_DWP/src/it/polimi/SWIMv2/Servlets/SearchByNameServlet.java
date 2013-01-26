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
 * Servlet implementation class SearchByNameServlet
 */
public class SearchByNameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private InitialContext ctx;
	
	private SearchBeanLocal sb;
	
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
			
			List<String> results = (List<String>)sb.searchByName(firstName, lastName);
			
			if(!results.isEmpty()){
				request.getSession().setAttribute("resultslist", results);
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
