package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
 * LoginFilter
 * @author amm
 *
 */
public class LoginFilter implements Filter{

	
	/**
     * Comprueba si el usuario está logado. Si no redirige a la pagaina de index.xhml
     */
    public void doFilter( final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
    	
    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse resp = (HttpServletResponse) response;
    	
//        if (!req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) { // Skip JSF resources (CSS/JS/Images/etc)
//            res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
//            res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
//            res.setDateHeader("Expires", 0); // Proxies.
//        }
    	
        // coge el ticket de session
        final ITicket ticket = (ITicket) ((HttpServletRequest)request).getSession().getAttribute("ticket");
         
        //comprueba si está logado.
        if (ticket == null || !ticket.isValid()) {
            String contextPath = req.getContextPath();
            resp.sendRedirect(contextPath + "/"+ NavigationMBean.INDEX_VIEW + ".xhtml");
        }

        chain.doFilter(request, response);
             
    }
 
    public void init(FilterConfig config) throws ServletException {
        // nada
    }
 
    public void destroy() {
        // nada
    }  

	
	
}
