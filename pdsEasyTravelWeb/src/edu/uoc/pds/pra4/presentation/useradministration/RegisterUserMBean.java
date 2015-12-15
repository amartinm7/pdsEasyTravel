package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.UserJPA;

/**
* Managed Bean RegisterDriverMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.REGISTER_USER_MBEAN)
@SessionScoped
public class RegisterUserMBean extends AbstractUserAdministration {
	
	private static final long serialVersionUID = 1L;
	
	private boolean showSuccess = false;
	
	private UserJPA userJPA;

	
	public RegisterUserMBean () {
		super();
		System.out.println(">>>init RegisterUserMBean...");
		resetModel();
	}
	
	public String registerUser () throws Exception {
		System.out.println(">>>registerUser...");
		try {
			
			if (userJPA.getUserType().equals( PassengerJPA.USER_TYPE )) {
				getUserFacade().registerPassenger( getTicket(), new PassengerJPA(userJPA) );
			} else {
				getUserFacade().registerDriver( getTicket(), new DriverJPA(userJPA) );
			}

			resetModel();
			showSuccess = true;
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( "Cuenta de usuario creada con exito" ));
		} catch (EasyTravelException e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			for ( ValidationError error : e.getErrors()) {
				FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( error.getMessageError() ));
			}
			return null;
		} catch (Exception e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			return null;
		}
		return null;
	}

	

	public UserJPA getUserJPA() {
		return userJPA;
	}

	public void setUserJPA(UserJPA userJPA) {
		this.userJPA = userJPA;
	}

	
	private void resetModel(){
		userJPA = new UserJPA();
	}
	
	/**
	 * coge el valor de la variable y la settea a false
	 * @return
	 */
	public boolean getShowSuccess() {
		return showSuccess;
	}


	public void setShowSuccess(boolean showSuccess) {
		this.showSuccess = showSuccess;
	}

	/**
	 * coge el valor de la variable y la settea a false
	 * @return
	 */
	public boolean getResetShowSuccess() {
		showSuccess = false;
		return true;
	}
	


}
