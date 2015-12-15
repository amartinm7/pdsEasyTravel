package edu.uoc.pds.pra4.presentation.useradministration;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.CarJPA;

/**
* Managed Bean ListCarsMBean
* @author amm
*/
@ManagedBean(name = MBeanNames.LIST_CARS_MBEAN)
@SessionScoped
public class ListCarsMBean extends AbstractUserAdministration {
	

	private static final long serialVersionUID = 1L;
	
	private List<CarJPA> listCarJPA;
	
	private boolean isThereRows;

	
	public ListCarsMBean() throws Exception {
		super();
		System.out.println(">>>init ListCarsMBean...");
		resetModel();

	}

	public List<CarJPA> getListCarJPA() {
		
		try {
			
			listCarJPA = getUserFacade().listAllCars( getTicket() );
			isThereRows = (listCarJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listCarJPA;
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
