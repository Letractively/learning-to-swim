package it.polimi.SWIMv2.SessionBeans;


import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.List;

import javax.ejb.Local;

@Local
public interface FriendshipBeanLocal {

	public abstract void friendshipRequest(String userEmail1, String userEmail2, boolean direct);
	
	public abstract void confirmFriendship(String userEmail1, String userEmail2);
	
	public List<String> getAllFriends(String userEmail);
	
	
	

}
