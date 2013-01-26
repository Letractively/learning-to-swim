package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;

import javax.ejb.Local;

@Local
public interface FriendProfileSessionBeanLocal {
	public abstract String getFriendData(String email);

}
