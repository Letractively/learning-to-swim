package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;

import javax.ejb.Local;

@Local
public interface UserSessionBeanLocal {
	public abstract GenericUser getDataFromDatabase(String email);

}
