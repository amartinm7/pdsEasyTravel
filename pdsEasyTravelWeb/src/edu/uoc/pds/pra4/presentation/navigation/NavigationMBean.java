package edu.uoc.pds.pra4.presentation.navigation;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;



@ManagedBean(name = MBeanNames.NAVIGATION_MBEAN)
@SessionScoped
public class NavigationMBean {

	
	public static final String SECURED_PREFIX = "/secured/";	
	
	public static final String INDEX_VIEW = "indexView";	
		
	public static final String MAIN_VIEW = "mainView";	

	public static final String CAR_LIST_VIEW = "carListView";
	
	public static final String UPDATE_PERSONAL_DATA_VIEW = "updatePersonalDataView";
	
	public static final String ADD_TRIP_VIEW = "addTripView";
	
	public static final String UPDATE_TRIP_INFORMATION_VIEW = "updateTripInformationView";
	
	public static final String FIND_MY_TRIPS_VIEW = "findMyTripsView";
	
	public static String redirectToIndexView () {
		return String.format("/%s?faces-redirect=true", INDEX_VIEW);
	}
	
	public static String toIndexView () {
		return String.format("/%s", INDEX_VIEW);
	}

	public static String toMainView () {
		return String.format("%s%s", SECURED_PREFIX, MAIN_VIEW);
	}
	
	public static String toUpdatePersonalDataView () {
		return String.format("%s%s", SECURED_PREFIX, UPDATE_PERSONAL_DATA_VIEW);
	}
	
	public static String toCarListView () {
		return String.format("%s%s", SECURED_PREFIX, CAR_LIST_VIEW);
	}
	
	public static String toUpdatePersonalDaListView () {
		return String.format("%s%s", SECURED_PREFIX, CAR_LIST_VIEW);
	}
	
	public static String toFindMyTripsView () {
		return String.format("%s%s", SECURED_PREFIX, FIND_MY_TRIPS_VIEW);
	}
	
	
}
