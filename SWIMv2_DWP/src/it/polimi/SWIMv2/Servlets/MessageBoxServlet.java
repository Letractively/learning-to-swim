package it.polimi.SWIMv2.Servlets;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.SessionBeans.MessageBeanLocal;

import java.io.IOException;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MessageBoxServlet
 */
public class MessageBoxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageBoxServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			InitialContext ctx = new InitialContext();
			MessageBeanLocal messageBean;
			
			messageBean = (MessageBeanLocal)ctx.lookup("MessageBean/local");
			
	        String userEmail = (String)request.getSession().getAttribute("email").toString();
	        List<String> inbox = messageBean.returnReceivedMessages(userEmail);
	        List<String> outbox = messageBean.returnSendedMessages(userEmail);
	        
	        request.getSession().setAttribute("inbox", inbox);
	        request.getSession().setAttribute("outbox", outbox);
	        
	        getServletConfig().getServletContext().getRequestDispatcher("/messagebox.jsp").forward(request, response);
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
