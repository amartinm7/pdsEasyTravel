package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
* Managed Bean LogoutMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LOGOUT_MBEAN)
@SessionScoped
public class LogoutMBean extends AbstractUserAdministration {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4278559198604473680L;
	
	
	public LogoutMBean(){
		super();
		System.out.println(">>>init LogoutMBean...");
	}
	
	public String logout(){
		System.out.println(">>>logout...");

		try {
			
			getUserFacade().logout( getTicket() );
			removeTicket();
			
		} catch (Exception e) {
			return null;
		}
		
		return NavigationMBean.redirectToIndexView();
	}

	
	private void removeTicket() {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().remove("ticket");
	}


	
}
