package edu.uoc.pds.pra4.presentation.useradministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.CarJPA;


/**
* Managed Bean AddCarMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.ADD_CAR_MBEAN)
@SessionScoped
public class AddCarMBean extends AbstractUserAdministration {
	

	private static final long serialVersionUID = 1L;
	
	private CarJPA carJPA; 
	
	public AddCarMBean() throws Exception {
		super();
		System.out.println(">>>init AddCarMBean...");
		resetModel();

	}
	
	public String addCar()  {
		System.out.println(">>>addCar...");
		
		try {
			
			getUserFacade().addCar( getTicket(), carJPA );
//			final String success = "La operación se ha realizado con exito.";
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( success ));
			resetModel();//clean car
			
		} catch (EasyTravelException e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return null;//ViewNames.MAIN_VIEW;
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
