package edu.uoc.pds.pra4.presentation.tripadministration;

import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.TripJPA;

/**
 * Managed Bean FindAllPassengersMBean
 */
@ManagedBean(name = MBeanNames.FIND_ALL_PASSENGERS_MBEAN )
@SessionScoped
public class FindAllPassengersMBean extends AbstractTripAdministration {
	
	private static final long serialVersionUID = 1L;	

	
	private Collection<PassengerJPA> listPassengerJPA;
	
	private Integer idTrip;
	
	private boolean isThereRows;
	
	private TripJPA tripJPA;


	
	public FindAllPassengersMBean() throws Exception {
		super();
		System.out.println(">>>init FindAllPassengersMBean...");
		resetModel();

	}
	


	public Collection<PassengerJPA> getListPassengerJPA() {
		
		try {
			
			listPassengerJPA = getTripAdministrationFacade().listPassengersByTrip( getTicket(), tripJPA );
			isThereRows = (listPassengerJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listPassengerJPA;
	}


	
	private void resetModel(){
		listPassengerJPA = new ArrayList<PassengerJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}

	
    public TripJPA getTripJPA() {
		return tripJPA;
	}



	public void setTripJPA(TripJPA tripJPA) {
		this.tripJPA = tripJPA;
	}
	
	public void setIdTrip(Integer idTrip) {
		
		if (tripJPA == null) {
			tripJPA = new TripJPA();
		}

        tripJPA.setId( idTrip );
		this.idTrip = idTrip;
	}
	
	
	public Integer getIdTrip() {
		
		return idTrip;
	}
}
