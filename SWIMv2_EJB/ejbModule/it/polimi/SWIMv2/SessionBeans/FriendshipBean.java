package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Friendship;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
	
/**
 * Session Bean implementation class FriendshipBean
 */
@Stateless
public class FriendshipBean implements FriendshipBeanLocal {
	
	@PersistenceContext(unitName="SWIMv2_PU")
	private EntityManager entityManager;
	  
	public FriendshipBean() {}
	
	@Override
	public void friendshipRequest(String userEmail1, String userEmail2, boolean direct) {
	    Query userQuery1 = entityManager.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
	    userQuery1.setParameter("email", userEmail1);
	   
	    Query userQuery2 = entityManager.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
	    userQuery2.setParameter("email", userEmail2);
	   
		try{
		    GenericUser user1 = (GenericUser)userQuery1.getSingleResult();
		    GenericUser user2 = (GenericUser)userQuery2.getSingleResult();
		   
		    Friendship friendship = new Friendship(user1, user2, direct); 
		    entityManager.persist(friendship);
		}
		catch(Exception e){
		    System.out.println(" Richiesta di amicizia non andata a buon fine ");
		}
	}
	
	@Override
	public void confirmFriendship(String userEmail1, String userEmail2) {
	    Query friendshipQuery = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail2 AND f.friendshipKey.friend2.email = :mail1");
	    friendshipQuery.setParameter("mail1", userEmail1);
		friendshipQuery.setParameter("mail2", userEmail2);
		
		try {
			Friendship friendship = (Friendship)friendshipQuery.getSingleResult();
			boolean confirmation = true;
			friendship.setConfirmation(confirmation);
			entityManager.persist(friendship);
		}
		catch(Exception e){
			System.out.println(" L'amicizia non e' stata confermata ");
		}
	}
	    
	@Override
	public List<String> getAllFriends(String userEmail) {
		try{
			Query friendsList1 = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail1 OR f.friendshipKey.friend2.email = :mail2");
			friendsList1.setParameter("mail1",userEmail);
			friendsList1.setParameter("mail2", userEmail);
		   
		    List<Friendship> friends = (List<Friendship>)friendsList1.getResultList();
		    return getParameters(friends,userEmail);
		}catch(Exception e){
			System.out.println("eccezione!");
			return null;
		}
	}
	
	/**
	* No side effects
	* 
	* @param friends
	* @return
	*/
	private List<String> getParameters(List<Friendship> friends, String userEmail) {
		List<String> result = new ArrayList<String>();
		for(Friendship f : friends){
			if(f.getFriendshipKey().getFriend1().getEmail().equals(userEmail)) {
				GenericUser friend = f.getFriendshipKey().getFriend2();
				String tupla = new String(friend.getFirstName()+ " " + friend.getLastName()+ " " + friend.getEmail() + " " + true);
				result.add(tupla);
			} else {
				GenericUser friend = f.getFriendshipKey().getFriend1();
				String tupla = new String(friend.getFirstName()+ " " + friend.getLastName()+ " " + friend.getEmail() + " " + f.isConfirmation());
				result.add(tupla);
			}
		}
		return result;
	}

	@Override
	public List<String> getHypoteticalIndirectFriends(String userMail, String friendMail) {
		try{
			Query friendsList1 = entityManager.createQuery("SELECT f.friendshipKey.friend2 FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail AND f.confirmation = :confirm ");
			friendsList1.setParameter("mail",friendMail);
			friendsList1.setParameter("confirm",true);
			

			Query friendsList2 = entityManager.createQuery("SELECT f.friendshipKey.friend1 FROM Friendship f WHERE f.friendshipKey.friend2.email = :mail AND f.confirmation = :confirm ");
			friendsList2.setParameter("mail",friendMail);
			friendsList2.setParameter("confirm",true);
		   
			List<GenericUser> totalFriends = new ArrayList<GenericUser>(mergeQueryResults(friendsList1,friendsList2));

			Query userList1 = entityManager.createQuery("SELECT f.friendshipKey.friend2 FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail AND f.confirmation = :confirm ");
			userList1.setParameter("mail",userMail);
			userList1.setParameter("confirm",true);

			Query userList2 = entityManager.createQuery("SELECT f.friendshipKey.friend1 FROM Friendship f WHERE f.friendshipKey.friend2.email = :mail AND f.confirmation = :confirm ");
			userList2.setParameter("mail",userMail);
			userList2.setParameter("confirm",true);
			
			List<GenericUser> userFriends = new ArrayList<GenericUser>(mergeQueryResults(userList1,userList2));
			List<GenericUser> potentialFriends = getPotentialFriends(totalFriends, userFriends);
			
			return getFriendsParameters(potentialFriends);
		}catch(Exception e){
			System.out.println("eccezione!");
			return null;
		}
	}
	
	private List<String> getFriendsParameters(List<GenericUser> potentialFriends) {
		List<String> friendsParameters = new ArrayList<String>();
		
		for(GenericUser pf: potentialFriends){
			friendsParameters.add(new String(pf.getFirstName() + "\t" + pf.getLastName() + "\t" + pf.getEmail()));
		}
		
		return friendsParameters;
	}

	private List<GenericUser> getPotentialFriends(List<GenericUser> totalFriends, List<GenericUser> userFriends) {
		List<GenericUser> potentialFriends = totalFriends;
		for(GenericUser pf: potentialFriends){
			for(GenericUser uf: userFriends){
				if(pf.getEmail().equals(uf.getEmail())){
					potentialFriends.remove(pf);
				}
			}
		}
		return potentialFriends;
	}

	private List<GenericUser> mergeQueryResults(Query q, Query q2) {
		try{
			List<GenericUser> l1 = (List<GenericUser>)q.getResultList();
			List<GenericUser> l2 = (List<GenericUser>)q2.getResultList();
			List<GenericUser> result = new ArrayList<GenericUser>();
		
			result.addAll(l1);
			result.addAll(l2);
			
			return result;
		}
		catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean areAlreadyFriends(String userEmail1, String userEmail2) {
		
		try {
			Query areFriends = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail1 AND f.friendshipKey.friend2.email = :mail2 OR f.friendshipKey.friend2.email = :mail3 AND f.friendshipKey.friend1.email = :mail4");
			
			areFriends.setParameter("mail1",userEmail1);
			areFriends.setParameter("mail2",userEmail2);
			areFriends.setParameter("mail3",userEmail1);
			areFriends.setParameter("mail4",userEmail2);
			
			areFriends.getSingleResult();
			
			return true;
		} catch(NoResultException e) {
			return false;
		} catch(Exception e) {
			return false;
		}
	}

	
	@Override
	public boolean areDirectFriends(String userEmail1, String userEmail2) {
		try {
			Query areDirectFriends = entityManager.createQuery("SELECT f.direct FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail1 AND f.friendshipKey.friend2.email = :mail2 AND f.direct = :direct1 OR f.friendshipKey.friend2.email = :mail3 AND f.friendshipKey.friend1.email = :mail4 AND f.direct = :direct2");
			
			areDirectFriends.setParameter("mail1",userEmail1);
			areDirectFriends.setParameter("mail2",userEmail2);
			areDirectFriends.setParameter("mail3",userEmail1);
			areDirectFriends.setParameter("mail4",userEmail2);
			areDirectFriends.setParameter("direct1",true);
			areDirectFriends.setParameter("direct2",true);
			
			return areDirectFriends.getSingleResult().equals(new Boolean(true));
		} catch(NoResultException e) {
			return false;
		}
		catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean isUnconfirmedFriendship(String userEmail1, String userEmail2) {
		try{
			Query areFriends = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail1 AND f.friendshipKey.friend2.email = :mail2 AND f.confirmation=:confirm1 OR f.friendshipKey.friend2.email = :mail3 AND f.friendshipKey.friend1.email = :mail4 AND f.confirmation=:confirm2");
			
			areFriends.setParameter("mail1",userEmail1);
			areFriends.setParameter("mail2",userEmail2);
			areFriends.setParameter("mail3",userEmail1);
			areFriends.setParameter("mail4",userEmail2);
			areFriends.setParameter("confirm1", false);
			areFriends.setParameter("confirm2", false);
			
			areFriends.getSingleResult();
			
			return true;
		} catch(NoResultException e) {
			return false;
		} catch(Exception e) {
			return false;
		}
	}
}

