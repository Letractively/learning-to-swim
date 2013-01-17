package it.polimi.SWIMv2.Filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class NonLoggedUsersFilter
 */
public class NonLoggedUsersFilter implements Filter {

    /**
     * Default constructor. 
     */
    public NonLoggedUsersFilter() {
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
		// TODO Auto-generated method stub
		// place your code here
		try{
			if((Boolean) ((HttpServletRequest)request).getSession().getAttribute("logged")){
				System.out.println("Posso accedere alla pagina del profilo");
			}
			else{
				System.out.println("faccio il redirect alla homepage");
				((HttpServletResponse) response).sendRedirect("index.jsp");
			}
		}catch (NullPointerException e){
			System.out.println("faccio il redirect alla homepage");
			((HttpServletResponse) response).sendRedirect("index.jsp");
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
