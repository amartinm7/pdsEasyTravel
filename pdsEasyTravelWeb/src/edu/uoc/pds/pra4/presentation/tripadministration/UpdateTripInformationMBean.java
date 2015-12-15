package edu.uoc.pds.pra4.presentation.tripadministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.TripJPA;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
* Managed Bean UpdateTripInformationMBean
*/
@ManagedBean(name = MBeanNames.UPDATE_TRIP_INFORMATION_MBEAN)
@SessionScoped
public class UpdateTripInformationMBean extends AbstractTripAdministration {


	private static final long serialVersionUID = 1L;
	
	private TripJPA tripJPA; 

	private Integer idTrip;

	
	
	public UpdateTripInformationMBean() throws Exception {
		super();
		System.out.println(">>>init UpdateTripInformationMBean...");
		resetModel();

	}
	
	public String updateTrip()  {
		System.out.println(">>>updateTrip...");
		
		try {
			
			getTripAdministrationFacade().updateTrip( getTicket(), tripJPA );
			resetModel();//clean car
			
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return NavigationMBean.toFindMyTripsView();
	}


	private void resetModel(){
		tripJPA = new TripJPA();
	}

	
	public void setIdTrip(Integer idTrip) {
		
		if (tripJPA == null) {
			tripJPA = new TripJPA();
		}

        tripJPA.setId( idTrip );
        try {
        	tripJPA = getTripAdministrationFacade().showTrip( getTicket(), tripJPA);
		} catch (EasyTravelException e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
		} catch (Exception e) {
			System.err.println( String.format ( "%s : %s ", this.getClass(), e.getMessage() ) );
		}

		this.idTrip = idTrip;
	}
	
	
	public Integer getIdTrip() {
		
		return idTrip;
	}
	
	public TripJPA getTripJPA() {
		
		return tripJPA;
	}

	public void setTripJPA(TripJPA tripJPA) {
		this.tripJPA = tripJPA;
	}
	
	
}
