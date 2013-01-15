package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.VoidSearchException;
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
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	private InitialContext ctx;
	
	private SearchBeanLocal sb;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//TODO rimuovere la println
		System.out.println(request.getParameter("nome")+", " + request.getParameter("cognome")+", " + request.getParameter("city")+", " + request.getParameter("ability"));
		List<String> parameters = generateSearchParameters(request.getParameter("nome"), request.getParameter("cognome"), request.getParameter("city"), request.getParameter("ability"));
	
		try {
			ctx = new InitialContext();
			sb = (SearchBeanLocal)ctx.lookup("SearchBean/local");
			List<GenericUser> risultati = sb.searchForUsers(parameters.get(0),parameters.get(1),parameters.get(2),parameters.get(3));
			if(risultati.isEmpty()){
				//TODO rimuovere la println
				System.out.println("la query ha prodotto un risultato vuoto (no exception)");
			}
			else{
				//TODO rimuovere la println
				System.out.println("Ho trovato " + risultati.get(0).getEmail());
			}
		} catch (NamingException e) {
			// TODO rimuovere la println
			System.out.println("qualcosa non va nella ricerca");
			e.printStackTrace();
		} catch (VoidSearchException e){
			//TODO rimuovere la println
			System.out.println("la query ha prodotto un risultato vuoto");
		}
	}


	private List<String> generateSearchParameters(String parameter, String parameter2, String parameter3, String parameter4) {
		String p1,p2,p3,p4;
		if(parameter == ""){
			p1 = null;
		}
		else{
			p1 = parameter;
		}
		if(parameter2 == ""){
			p2 = null;
		}
		else{
			p2 = parameter2;
		}
		if(parameter3 == ""){
			p3 = null;
		}
		else{
			p3 = parameter3;
		}
		if(parameter4 == ""){
			p4 = null;
		}
		else{
			p4 = parameter4;
		}
		List<String> result = new ArrayList<String>();
		result.add(p1);
		result.add(p2);
		result.add(p3);
		result.add(p4);
		
		return result;
	}

}
