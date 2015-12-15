package edu.uoc.pds.pra4.presentation.tripadministration;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.TripJPA;
import edu.uoc.pds.pra4.presentation.navigation.NavigationMBean;

/**
* Managed Bean AddTripMBean
*/
@ManagedBean(name = MBeanNames.ADD_TRIP_MBEAN )
@SessionScoped
public class AddTripMBean extends AbstractTripAdministration{
	
	private static final long serialVersionUID = 1L;
	
	private TripJPA tripJPA; 

	
	public AddTripMBean() throws Exception {
		super();
		System.out.println(">>>init AddTripMBean...");		
		resetModel();

	}
	
	public String addTrip()  {
		System.out.println(">>>addTrip...");
		
		try {
			
			getTripAdministrationFacade().addTrip( getTicket(), tripJPA );
			resetModel();//clean trip
			
		} catch (EasyTravelException e) {
			System.err.println( String.format (  "%s : %s ", this.getClass(), e.getMessage() ) );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		return NavigationMBean.toFindMyTripsView();
	}




	public TripJPA getTripJPA() {
		return tripJPA;
	}

	public void setTripJPA(TripJPA tripJPA) {
		this.tripJPA = tripJPA;
	}


	private void resetModel(){
		tripJPA = new TripJPA();
	}



}
