package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.AlreadyValidatedUserException;

import javax.ejb.Local;

@Local
public interface ActivationBeanLocal {
	
	public abstract GenericUser activateAccount(String email, String validation) throws AlreadyValidatedUserException;

	public abstract GenericUser getUserFromEmail(String email);

}
