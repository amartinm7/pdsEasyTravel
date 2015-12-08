package edu.uoc.pds.pra4.integration;

import java.io.Serializable;

public interface IUserJPA extends Serializable, IValidable{

	public String getNif() ;

	public void setNif(String nif) ;

	public String getName();

	public void setName(String name) ;

	public String getSurname() ;

	public void setSurname(String surname);

	public String getPhone() ;

	public void setPhone(String phone) ;

	public String getPassword() ;

	public void setPassword(String password) ;

	public String getEmail() ;

	public void setEmail(String email) ;
	
	public String getUserType() ;

	public void setUserType(String userType);
	
	public boolean isDriver();
	
	
	
}
