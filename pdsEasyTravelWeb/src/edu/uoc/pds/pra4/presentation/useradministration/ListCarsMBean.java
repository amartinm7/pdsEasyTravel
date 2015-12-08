package edu.uoc.pds.pra4.presentation.useradministration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
* Managed Bean ListCarsMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LIST_CARS_MBEAN)
@SessionScoped
public class ListCarsMBean implements Serializable, IUserAdministration{
	

	private static final long serialVersionUID = 1L;
	
	private List<CarJPA> listCarJPA;
	
	private boolean isThereRows;

	private ITicket ticket;
	
    @ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;

	
	public ListCarsMBean() throws Exception {
		super();
		System.out.println(">>>init ListCarsMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	


	public List<CarJPA> getListCarJPA() {
		
		try {
			
			listCarJPA = getUserFacade().listAllCars( ticket );
			isThereRows = (listCarJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listCarJPA;
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
	
	public void setListCarJPA(List<CarJPA> listCarJPA) {
		this.listCarJPA = listCarJPA;
	}
	
	private void resetModel(){
		listCarJPA = new ArrayList<CarJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}
	

}
