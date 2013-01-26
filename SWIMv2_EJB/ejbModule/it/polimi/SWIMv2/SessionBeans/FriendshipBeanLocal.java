package it.polimi.SWIMv2.SessionBeans;


import java.util.List;

import javax.ejb.Local;

@Local
public interface FriendshipBeanLocal {

	public abstract void friendshipRequest(String userEmail1, String userEmail2, boolean direct);
	
	public abstract void confirmFriendship(String userEmail1, String userEmail2);
	
	public abstract List<String> getAllFriends(String userEmail);
	
	public abstract List<String> getHypoteticalIndirectFriends(String userMail,String friendMail);
	
	public abstract boolean areAlreadyFriends(String userEmail1, String userEmail2);
  
	public abstract Boolean areDirectFriends(String userEmail1, String userEmail2);

}
