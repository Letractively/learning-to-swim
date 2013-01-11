package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.Exceptions.UserNotFoundException;

import javax.ejb.Local;

@Local
public interface LoginBeanLocal {
	
	public abstract boolean validateUser(String email, String hashedPassword) throws UserNotFoundException;

}
