package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.Ticket;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
* Managed Bean LoginMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LOGIN_MBEAN)
@SessionScoped
public class LoginMBean extends AbstractUserAdministration {
	

	private static final long serialVersionUID = 1L;
	
	private boolean showError = false;
	
	private String email;
	
	private String password;
	

	public LoginMBean() {
		super();
		System.out.println(">>>init LoginMBean...");
		resetModel();
	}

	
	public String login()  {	
		
		System.out.println(">>>login...");
		
		try {
			
			ITicket ticket = getUserFacade().login(email, password);
			setTicket(ticket);
			resetModel();
			
		} catch (EasyTravelException e) {
			showError = true;
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			//FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return NavigationMBean.redirectToIndexView();
			
		} catch (Exception e) {
			
			return NavigationMBean.redirectToIndexView();
		}

		
		return NavigationMBean.toMainView();
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


	private void setTicket( ITicket ticket ) {
		
		if (ticket == null) {
			ticket = Ticket.getInvalidTicket(email, password);//init ticket
		}
		
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getSessionMap().put("ticket", ticket);
	}
	
	private void resetModel(){
		email = "";
		password = "";	
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
