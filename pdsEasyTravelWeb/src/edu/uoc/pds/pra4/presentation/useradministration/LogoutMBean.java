package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.Ticket;
import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
* Managed Bean LogoutMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LOGOUT_MBEAN)
@SessionScoped
public class LogoutMBean implements Serializable, IUserAdministration{

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 4278559198604473680L;
	

	
	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	public LogoutMBean(){
		super();
		System.out.println(">>>init LogoutMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
	}
	
	public String logout(){
		System.out.println(">>>logout...");

		try {
			
			getUserFacade().logout( getTicket() );
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getSessionMap().remove("ticket");	
			
		} catch (Exception e) {
			return null;
		}
		
		return NavigationMBean.redirectToIndexView();
	}
	
	@Override
	public UserFacadeLocal getUserFacade() throws Exception {
		
		return facadeServicesEJB.getUserFacade();

	}

	public FacadeServicesEJB getFacadeServicesEJB() {
		return facadeServicesEJB;
	}

	public void setFacadeServicesEJB(FacadeServicesEJB facadeServicesEJB) {
		this.facadeServicesEJB = facadeServicesEJB;
	}
	
	public ITicket getTicket() {
		
		if (ticket == null) {
			ticket = Ticket.getInvalidTicket("", "");//init ticket
		}
		return ticket;
	}




	
}
