package it.polimi.SWIMv2.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class NonAdminUsersCannotCreateAbilitiesFilter
 */
public class NonAdminUsersCannotCreateAbilitiesFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NonAdminUsersCannotCreateAbilitiesFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		
		if(req.getSession().getAttribute("type") == null){
			req.getRequestDispatcher("/index.jsp").forward(request, response);
		}
		else if(req.getSession().getAttribute("type").equals("utente normale")){
			req.getRequestDispatcher("/profile.jsp").forward(request, response);
		}
		else{
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
