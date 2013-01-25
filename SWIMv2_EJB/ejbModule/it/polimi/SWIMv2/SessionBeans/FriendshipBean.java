	package it.polimi.SWIMv2.SessionBeans;
	
	import it.polimi.SWIMv2.EntityBeans.Friendship;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
	
	import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
	
import javassist.tools.framedump;

	import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
	    Query friendshipQuery = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friend1.email = :mail2 AND f.friend2.email = :mail1");
	     	friendshipQuery.setParameter("mail1", userEmail1);
	friendshipQuery.setParameter("mail2", userEmail2);
	try{
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
		Query friendsList1 = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend1.email = :mail ");
		
		friendsList1.setParameter("mail",userEmail);
		
		
		Query friendsList2 = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendshipKey.friend2.email = :mail ");
		
		friendsList2.setParameter("mail", userEmail);
		
	   
	    List<Friendship> friends = new ArrayList<Friendship>(mergeQueryResults(friendsList1,friendsList2));
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
			if(f.getFriendshipKey().getFriend1().getEmail().equals(userEmail)){
				GenericUser friend = f.getFriendshipKey().getFriend2();
				String tupla = new String(friend.getFirstName()+ " " + friend.getLastName()+ " " + friend.getEmail() + " " + f.isConfirmation());
				result.add(tupla);
			}else{
				GenericUser friend = f.getFriendshipKey().getFriend1();
				String tupla = new String(friend.getFirstName()+ " " + friend.getLastName()+ " " + friend.getEmail() + " " + f.isConfirmation());
				result.add(tupla);
			}
				
		
		}
		return result;
	}
	
	
	
	private List<Friendship> mergeQueryResults(Query q, Query q2) {
		try{
			List<Friendship> l1 = (List<Friendship>)q.getResultList();
			List<Friendship> l2 = (List<Friendship>)q2.getResultList();
			List<Friendship> result = new ArrayList<Friendship>();
			return mergeLists(result, l1,l2);
		}
		catch(Exception e){
			
		return null;
	}
	}
	
	private List<Friendship> mergeLists(List<Friendship> result, List<Friendship> l1, List<Friendship> l2) {
	result.addAll(l1);
	result.addAll(l2);
	Set<Friendship> resultSet = new HashSet<Friendship>(result);
	return new ArrayList<Friendship>(resultSet);
	}
	
	
	}

