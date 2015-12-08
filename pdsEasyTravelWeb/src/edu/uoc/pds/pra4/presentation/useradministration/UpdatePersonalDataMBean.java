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
import edu.uoc.pds.pra4.integration.UserJPA;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
* Managed Bean UpdatePersonalDataMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.UPDATE_PERSONAL_DATA_MBEAN)
@SessionScoped
public class UpdatePersonalDataMBean implements Serializable, IUserAdministration{

	
	
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 2879489712880346072L;

    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	
	private ITicket ticket;
	
	private UserJPA userJPA;
	
	
	public UpdatePersonalDataMBean(){
		super();
		System.out.println(">>>init UpdatePersonalDataMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");
		resetModel();
	}
	
	public String updatePersonalData () throws Exception {
		System.out.println(">>>RegisterDriver...");
		try {
			
			getUserFacade().updatePersonalData(ticket, userJPA);
//			final String success = "La operación se ha realizado con exito.";
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( success ));
			resetModel();
			
		} catch (EasyTravelException e) {
			for ( ValidationError error : e.getErrors()) {
				FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( error.getMessageError() ));
			}
			return null;
		} catch (Exception e) {
			return null;
		}
		return NavigationMBean.toMainView();
	}
	

	public UserJPA getUserJPA() {
		
		if ( userJPA == null || userJPA.getNif() == null || userJPA.getNif().equals("") ) {
			
			try {
				userJPA = getUserFacade().getUserJPA(ticket);
			} catch (Exception e) {
				return null;
			}
		}
		
		return userJPA;
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
		return ticket;
	}


	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}

	
	
	public void setUserJPA(UserJPA userJPA) {
		this.userJPA = userJPA;
	}
	
	private void resetModel(){
		userJPA = new UserJPA();
	}
	
	
	
}
