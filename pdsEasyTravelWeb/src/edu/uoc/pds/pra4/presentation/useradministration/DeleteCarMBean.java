package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.CarJPA;

/**
* Managed Bean DeleteCarMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.DELETE_CAR_MBEAN)
@SessionScoped
public class DeleteCarMBean  extends AbstractUserAdministration {
	

	private static final long serialVersionUID = 1L;
	
	private CarJPA carJPA; 


	public DeleteCarMBean() throws Exception {
		super();
		System.out.println(">>>init DeleteCarMBean...");	
		resetModel();
		
	}
	
	public String deleteCar()  {
		System.out.println(">>>deleteCar...");
		
		try {
			
	        FacesContext facesContext = FacesContext.getCurrentInstance();
	        carJPA.setCarRegistrationId( Integer.valueOf( facesContext.getExternalContext().getRequestParameterMap().get("carRegistrationId") ) );
			
			getUserFacade().deleteCar( getTicket(), carJPA );
//			final String success = "La operación se ha realizado con exito.";
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( success ));
			resetModel();
		} catch (EasyTravelException e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return null;
	}

	
	
	public CarJPA getCarJPA() {
		return carJPA;
	}

	public void setCarJPA(CarJPA carJPA) {
		this.carJPA = carJPA;
	}
	
	private void resetModel(){
		carJPA = new CarJPA();
	}

}
