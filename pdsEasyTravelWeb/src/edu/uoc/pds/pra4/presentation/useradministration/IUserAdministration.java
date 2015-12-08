package edu.uoc.pds.pra4.presentation.useradministration;

import edu.uoc.pds.pra4.business.useradministration.UserFacadeLocal;

public interface IUserAdministration {

	public UserFacadeLocal getUserFacade() throws Exception;
}
