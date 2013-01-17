package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;

import javax.ejb.Local;

@Local
public interface FriendshipBeanLocal {

	public abstract void setFriendshipRequested(GenericUser u1, GenericUser u2, boolean type);
	
	public abstract void setFriendship(GenericUser u1, GenericUser u2);
}
