package edu.uoc.pds.pra4.business.useradministration;

import java.util.List;

import javax.ejb.Remote;

import edu.uoc.pds.pra4.exception.EasyTravelException;
import edu.uoc.pds.pra4.integration.CarJPA;
import edu.uoc.pds.pra4.integration.DriverJPA;
import edu.uoc.pds.pra4.integration.IUserJPA;
import edu.uoc.pds.pra4.integration.PassengerJPA;
import edu.uoc.pds.pra4.integration.UserJPA;

/**
 * UserFacadeRemote
 * @author amm
 */
@Remote
public interface UserFacadeRemote {

	/**
	 * login
	 * @param email
	 * @param password
	 * @return ITicket
	 * @throws EasyTravelException
	 */
	public ITicket login ( final String email, final String password ) throws EasyTravelException;
	
	/**
	 * logout
	 * @param ticket
	 */
	public void logout ( final ITicket ticket );
	
	/**
	 * registerDriver
	 * @param ticket
	 * @param driverJPA
	 * @throws EasyTravelException
	 */
	public void registerDriver ( final ITicket ticket, DriverJPA driverJPA ) throws EasyTravelException;
	
	/**
	 * updatePersonalData
	 * @param ticket
	 * @param userJPA
	 * @throws EasyTravelException
	 */
	public void updatePersonalData ( final ITicket ticket, final IUserJPA userJPA ) throws EasyTravelException; 		
	
	/**
	 * registerPassenger
	 * @param ticket
	 * @param passengerJPA
	 * @throws EasyTravelException
	 */
	public void registerPassenger ( final ITicket ticket, final PassengerJPA passengerJPA ) throws EasyTravelException;
	
	/**
	 * addCar
	 * @param ticket
	 * @param carJPA
	 * @throws EasyTravelException
	 */
	public void addCar ( final ITicket ticket, final CarJPA carJPA) throws EasyTravelException ;
	
	/**
	 * listAllCars
	 * @param ticket
	 * @return
	 * @throws EasyTravelException
	 */
	public List<CarJPA> listAllCars ( final ITicket ticket ) throws EasyTravelException;
	
	/**
	 * deleteCar
	 * @param ticket
	 * @param carJPA
	 * @throws EasyTravelException
	 */
	public void deleteCar ( final ITicket ticket, final CarJPA carJPA ) throws EasyTravelException;
	
	/**
	 * getUserJPA
	 * @param ticket
	 * @return
	 * @throws EasyTravelException
	 */
	public UserJPA getUserJPA ( final ITicket ticket ) throws EasyTravelException;
	
}
