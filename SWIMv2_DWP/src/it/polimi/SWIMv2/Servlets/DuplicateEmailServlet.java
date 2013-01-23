package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.RegistrationBeanLocal;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DuplicateEmailServlet
 */
public class DuplicateEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private InitialContext ctx;
	private RegistrationBeanLocal rb;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DuplicateEmailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			ctx = new InitialContext();
			rb = (RegistrationBeanLocal)ctx.lookup("RegistrationBean/local");
			
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			
			String email = request.getParameter("email");
		    if((boolean)rb.isEmailAvailable(email)){
		    	out.println("");
		    }
		    else{
		    	out.println("Email non disponibile: inserirne un'altra");
		    }
		    
		}
		catch(NamingException e){
			PrintWriter out = response.getWriter();
			out.println("Errore: ricarica la pagina e riprova");
		}
	}

}
