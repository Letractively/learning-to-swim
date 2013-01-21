package it.polimi.SWIMv2.SessionBeans;


import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.List;

import javax.ejb.Local;

@Local
public interface FriendshipBeanLocal {

	public abstract void setFriendshipRequest(String email1, String email2, boolean type);
	
	public abstract void confirmFriendship(GenericUser User1, GenericUser User2);
	
	public List<GenericUser> getAllFriends(GenericUser u);
	
	public List<GenericUser> getTypeABFriends(GenericUser u, boolean type);
	

}
