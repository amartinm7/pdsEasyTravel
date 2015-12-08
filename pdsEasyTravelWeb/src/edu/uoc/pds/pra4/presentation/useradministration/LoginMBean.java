package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.Ticket;
import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
* Managed Bean LoginMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LOGIN_MBEAN)
@SessionScoped
public class LoginMBean implements Serializable, IUserAdministration{
	

	private static final long serialVersionUID = 1L;
	
	private boolean showError = false;
	
	private String email;
	
	private String password;
	
	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;

	
	public LoginMBean() {
		super();
		ticket = Ticket.getInvalidTicket(email, password);//init ticket
		System.out.println(">>>init LoginMBean...");
		resetModel();
		//TODO resetear el modelo email y password, ticket
	}

	
	public String login()  {	
		
		System.out.println(">>>login...");
		
		try {
			
			ticket = getUserFacade().login(email, password);
			
		} catch (EasyTravelException e) {
			showError = true;
			System.err.println( e.getMessage() );
			//FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return NavigationMBean.redirectToIndexView();
			
		} catch (Exception e) {
			
			return NavigationMBean.redirectToIndexView();
		}
		

		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("ticket", ticket);
		
		return NavigationMBean.toMainView();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ITicket getTicket() {
		
		if (ticket == null) {
			ticket = Ticket.getInvalidTicket(email, password);//init ticket
		}
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}
	
	private void resetModel(){
		email = "";
		password = "";	
		ticket = Ticket.getInvalidTicket(email, password);//init ticket
	}
	
	public boolean getLoggedIn(){
	
		return getTicket().isValid();
	}
	
	public boolean getIsADriver(){

		return getTicket().isDriver();
	}

	/**
	 * coge el valor de la variable y la settea a false
	 * @return
	 */
	public boolean getResetShowError() {
		showError = false;
		return true;
	}

	public boolean getShowError() {
		return showError;
	}

	public void setShowError(boolean showError) {
		this.showError = showError;
	}

}
