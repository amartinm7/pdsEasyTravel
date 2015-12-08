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
    	
        // coge el LoginMBean de session
        final LoginMBean loginMBean = (LoginMBean)((HttpServletRequest)request).getSession().getAttribute( MBeanNames.LOGIN_MBEAN );
         
        //comprueba si está logado.
        if (loginMBean == null || !loginMBean.getLoggedIn()) {
            String contextPath = ((HttpServletRequest)request).getContextPath();
            ((HttpServletResponse)response).sendRedirect(contextPath + "/ "+ NavigationMBean.INDEX_VIEW );
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
