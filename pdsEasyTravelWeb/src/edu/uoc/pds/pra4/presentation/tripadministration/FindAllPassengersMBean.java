package edu.uoc.pds.pra4.presentation.tripadministration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;
import edu.uoc.pds.pra4.business.useradministration.ITicket;
import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.TripJPA;
import edu.uoc.pds.pra4.presentation.utils.FacadeServicesEJB;

/**
 * Managed Bean FindAllPassengersMBean
 */
@ManagedBean(name = MBeanNames.FIND_ALL_PASSENGERS_MBEAN )
@SessionScoped
public class FindAllPassengersMBean implements Serializable, ITripAdministration{
	
	private static final long serialVersionUID = 1L;	

	
	private Collection<PassengerJPA> listPassengerJPA;
	
	private Integer idTrip;
	
	private boolean isThereRows;

	private ITicket ticket;
	
	private TripJPA tripJPA;


	@ManagedProperty( value = "#{facadeServicesEJB}" )
    private FacadeServicesEJB facadeServicesEJB;

	
	public FindAllPassengersMBean() throws Exception {
		super();
		System.out.println(">>>init FindAllPassengersMBean...");
		FacesContext context = FacesContext.getCurrentInstance();
		ticket = (ITicket) context.getExternalContext().getSessionMap().get("ticket");		
		resetModel();

	}
	


	public Collection<PassengerJPA> getListPassengerJPA() {
		
		try {
			
			listPassengerJPA = getTripAdministrationFacade().listPassengersByTrip( ticket, tripJPA );
			isThereRows = (listPassengerJPA.size() > 0 );
			
		} catch (EasyTravelException e) {
			System.err.println( e.getMessage() );
			FacesContext.getCurrentInstance().addMessage("errors", new FacesMessage( e.getMessage() ) );
			return null;
		} catch (Exception e) {
			return null;
		}
		
		
		return listPassengerJPA;
	}



	public ITicket getTicket() {
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}


	public FacadeServicesEJB getFacadeServicesEJB() {
		return facadeServicesEJB;
	}

	public void setFacadeServicesEJB(FacadeServicesEJB facadeServicesEJB) {
		this.facadeServicesEJB = facadeServicesEJB;
	}
	

	
	private void resetModel(){
		listPassengerJPA = new ArrayList<PassengerJPA>();
		isThereRows = false;
	}
	
	public boolean getIsThereRows() {
		return isThereRows;
	}



	@Override
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {
		return facadeServicesEJB.getTripAdministrationFacade();
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
