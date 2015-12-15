package edu.uoc.pds.pra4.presentation.tripadministration;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.TripJPA;

/**
 * Managed Bean FindMyTripsMBean
 */
@ManagedBean(name = MBeanNames.FIND_MY_TRIPS_MBEAN )
@SessionScoped
public class FindMyTripsMBean extends AbstractTripAdministration{

	private static final long serialVersionUID = 1L;
	
	private Collection<TripJPA> listTripJPA;
	
	private boolean isThereRows;

	
	public FindMyTripsMBean() throws Exception {
		super();
		System.out.println(">>>init FindMyTripsMBean...");
		resetModel();

	}

	public Collection<TripJPA> getListTripJPA() {
		
		try {
			
			listTripJPA = getTripAdministrationFacade().listTrips ( getTicket() );
			isThereRows = (listTripJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listTripJPA;
	}


	private void resetModel(){
		listTripJPA = new ArrayList<TripJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}

	
	
}
