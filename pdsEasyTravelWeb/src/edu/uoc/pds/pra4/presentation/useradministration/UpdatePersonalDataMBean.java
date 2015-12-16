package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.exception.ValidationError;
import edu.uoc.pds.pra4.integration.UserJPA;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
* Managed Bean UpdatePersonalDataMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.UPDATE_PERSONAL_DATA_MBEAN)
@SessionScoped
public class UpdatePersonalDataMBean extends AbstractUserAdministration {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2879489712880346072L;

	private UserJPA userJPA;
	
	
	public UpdatePersonalDataMBean(){
		super();
		System.out.println(">>>init UpdatePersonalDataMBean...");
		resetModel();
	}
	
	public String updatePersonalData () throws Exception {
		System.out.println(">>>RegisterDriver...");
		try {
			
			getUserFacade().updatePersonalData( getTicket(), userJPA);
//			final String success = "La operación se ha realizado con exito.";
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( success ));
			resetModel();
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			for ( ValidationError error : e.getErrors()) {
				FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( error.getMessageError() ));
			}
			return null;
		} catch (Exception e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			return null;
		}
		return NavigationMBean.toMainView();
	}
	

	public UserJPA getUserJPA() {
		
		try {
			userJPA = getUserFacade().getUserJPA( getTicket() );
		} catch (Exception e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			return null;
		}
		
		return userJPA;
	}

	
	public void setUserJPA(UserJPA userJPA) {
		this.userJPA = userJPA;
	}
	
	private void resetModel(){
		userJPA = new UserJPA();
	}
	
	
	
}
