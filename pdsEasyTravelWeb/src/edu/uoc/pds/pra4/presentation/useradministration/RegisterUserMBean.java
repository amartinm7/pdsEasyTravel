package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.UserJPA;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
* Managed Bean RegisterDriverMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.REGISTER_USER_MBEAN)
@SessionScoped
public class RegisterUserMBean implements Serializable, IUserAdministration{
	
	private static final long serialVersionUID = 1L;
	
	private boolean showSuccess = false;
	
	private UserJPA userJPA;
	
	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	public RegisterUserMBean () {
		super();
		System.out.println(">>>init RegisterUserMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");
		resetModel();
	}
	
	public String registerUser () throws Exception {
		System.out.println(">>>registerUser...");
		try {
			
			if (userJPA.getUserType().equals( PassengerJPA.USER_TYPE )) {
				getUserFacade().registerPassenger(ticket, new PassengerJPA(userJPA) );
			} else {
				getUserFacade().registerDriver( ticket, new DriverJPA(userJPA) );
			}

			resetModel();
			showSuccess = true;
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( "Cuenta de usuario creada con exito" ));
		} catch (EasyTravelException e) {
			for ( ValidationError error : e.getErrors()) {
				FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( error.getMessageError() ));
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		return null;
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
