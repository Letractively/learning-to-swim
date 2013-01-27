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
			
			String feedback = request.getParameter("feedbackValue");
			String email = request.getParameter("feedbackEmail");
			Integer value = Integer.parseInt(feedback);
			boolean feedbackCorrectlyGiven = fb.giveFeedback(email, value);
			
			if(feedbackCorrectlyGiven){
				request.getSession().setAttribute("alert", "Feedback dato con successo!");
			    getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
			}
			else{
				request.getSession().setAttribute("alert", "Feedback dato con successo!");
			    getServletConfig().getServletContext().getRequestDispatcher("/profile.jsp").forward(request, response);
			}
		}
		catch(NamingException e){
			e.printStackTrace();
		}
		catch(Exception e){
			System.out.println("Feedback non aggiunto correttamente");
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
}
