package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.SessionBeans.FeedbackBeanLocal;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FeedbackServlet
 */
public class FeedbackServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private InitialContext ctx;
	private FeedbackBeanLocal fb;
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			ctx = new InitialContext();
			fb = (FeedbackBeanLocal)ctx.lookup("FeedbackBean/local");
			
			String[] feedback = request.getParameterValues("feedback") ;
			//Integer value = Integer.parseInt(request.getParameter("SOSTITUIRE COL NOME DEL PARAMETRO PRESENTE NELLA PAGINA JSP"));
			String email = (String)request.getSession().getAttribute("email");
			Integer value = Integer.parseInt(feedback[0]);
			boolean feedbackCorrectlyGiven = fb.giveFeedback(email, value);
			
			if(feedbackCorrectlyGiven){
				request.getSession().setAttribute("feedbackgiven", "Feedback dato con successo!");
			    getServletConfig().getServletContext().getRequestDispatcher("/messagebox").forward(request, response);
			}
			else{
				System.out.println("Feedback non aggiunto correttamente");
			}
		}
		catch(NamingException e){
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Feedback non aggiunto correttamente");
		}
		
		
		
	}

}
