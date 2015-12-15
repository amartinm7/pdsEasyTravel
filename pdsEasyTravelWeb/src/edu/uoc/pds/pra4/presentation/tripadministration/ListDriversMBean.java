package edu.uoc.pds.pra4.presentation.tripadministration;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.DriverJPA;

/**
 * Managed Bean ListDriversMBean
 */
@ManagedBean(name = MBeanNames.LIST_DRIVERS_MBEAN )
@SessionScoped
public class ListDriversMBean extends AbstractTripAdministration {
	
	private static final long serialVersionUID = 1L;
	
	
	private Collection<DriverJPA> listDriversJPA;
	
	private boolean isThereRows;


	
	public ListDriversMBean() throws Exception {
		super();
		System.out.println(">>>init ListDriversMBean...");	
		resetModel();

	}
	


	public Collection<DriverJPA> getListTripJPA() {
		
		try {
			
			listDriversJPA = getTripAdministrationFacade().listAllDrivers( getTicket() );
			isThereRows = (listDriversJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listDriversJPA;
	}

	

	
	private void resetModel(){
		listDriversJPA = new ArrayList<DriverJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}


	public Collection<DriverJPA> getListDriversJPA() {
		return listDriversJPA;
	}



	public void setListDriversJPA(Collection<DriverJPA> listDriversJPA) {
		this.listDriversJPA = listDriversJPA;
	}



	public void setThereRows(boolean isThereRows) {
		this.isThereRows = isThereRows;
	}
	
	
 
}
