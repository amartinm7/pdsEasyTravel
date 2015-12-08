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
import edu.uoc.pds.pra4.integration.CarJPA;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;


/**
* Managed Bean AddCarMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.ADD_CAR_MBEAN)
@SessionScoped
public class AddCarMBean implements Serializable, IUserAdministration{
	

	private static final long serialVersionUID = 1L;
	
	private CarJPA carJPA; 

	private ITicket ticket;

    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;
	
	
	public AddCarMBean() throws Exception {
		super();
		System.out.println(">>>init AddCarMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	
	public String addCar()  {
		System.out.println(">>>addCar...");
		
		try {
			
			getUserFacade().addCar( ticket, carJPA );
//			final String success = "La operación se ha realizado con exito.";
//			FacesContext.getCurrentInstance().addMessage("success", new FacesMessage( success ));
			resetModel();//clean car
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return null;//ViewNames.MAIN_VIEW;
	}

	public ITicket getTicket() {
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
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
