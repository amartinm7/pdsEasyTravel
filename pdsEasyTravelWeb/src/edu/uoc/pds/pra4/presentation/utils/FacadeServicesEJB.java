package edu.uoc.pds.pra4.presentation.utils;

import java.io.Serializable;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.naming.Context;
import javax.naming.InitialContext;

import edu.uoc.pds.pra4.business.tripadministration.TripAdministrationFacadeLocal;
import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;


/**
 * LookUpNames
 * @author amm
 */
@ManagedBean(name = MBeanNames.FACADE_SERVICES_EJB_MBEAN)
@SessionScoped
public class FacadeServicesEJB implements Serializable {

	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;

	public static final String USER_FACADE_BEAN_LOOKUP = "java:app/pdsEasyTravelEJB.jar/UserFacadeBean!ejb.UserFacadeLocal";
	
	public static final String TRIP_ADMINISTRATION_BEAN_LOOKUP = "java:app/PracticaEJB.jar/TripAdministrationFacadeBean!ejb.TripAdministrationFacadeLocal";
	
	@EJB
	private UserFacadeLocal userFacade;
	
	@EJB
	private TripAdministrationFacadeLocal tripAdministrationFacade;
	
	
	
	
	public UserFacadeLocal getUserFacade() throws Exception {
		
		try {
			
			if ( userFacade == null ) {

				final Properties props = System.getProperties();
				final Context ctx = new InitialContext(props);
				userFacade = (UserFacadeLocal) ctx.lookup( FacadeServicesEJB.USER_FACADE_BEAN_LOOKUP );
			}
			
		} catch (Exception e) {
			
			System.err.println( String.format("No se ha podido obtener una referencia del EJB en cuestion. Revisar el lookup. %s : %s", this.getClass(), e.getMessage() ) );
			throw e;
		}

		return userFacade;
	}
	
	public TripAdministrationFacadeLocal getTripAdministrationFacade() throws Exception {

		try {
			
			if ( tripAdministrationFacade == null ) {
			
				final Properties props = System.getProperties();
				final Context ctx = new InitialContext(props);
				tripAdministrationFacade = (TripAdministrationFacadeLocal) ctx.lookup( FacadeServicesEJB.TRIP_ADMINISTRATION_BEAN_LOOKUP );
			}
			
		} catch (Exception e) {
			
			System.err.println( String.format("No se ha podido obtener una referencia del EJB en cuestion. Revisar el lookup. %s : %s", this.getClass(), e.getMessage() ) );
			throw e;
		}


		return tripAdministrationFacade;
	}
	
}
