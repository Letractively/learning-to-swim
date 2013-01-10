package it.polimi.SWIMv2.SessionBeans;

import javax.ejb.Local;

@Local
public interface RegistrationBeanLocal {

	public abstract void insertIntoDatabase(String firstName, String lastName, String email, String password, String city);
}
