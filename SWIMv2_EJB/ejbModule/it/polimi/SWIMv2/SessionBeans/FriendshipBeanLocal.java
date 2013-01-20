package it.polimi.SWIMv2.SessionBeans;


import javax.ejb.Local;

@Local
public interface FriendshipBeanLocal {

	public abstract void friendshipRequest(String userEmail1, String userEmail2, boolean direct);
	
	public abstract void confirmationFriendship(Long userId1, Long userId2);
	

}
