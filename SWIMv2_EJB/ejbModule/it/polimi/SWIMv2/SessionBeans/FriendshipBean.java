package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Friendship;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FriendshipBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void setFriendshipRequest(String email1, String email2, boolean type) {

    	Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
    	q.setParameter("email", email1);
    	
    	Query q2 = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
    	q2.setParameter("email", email2);
    	
    	try{
    		GenericUser u1 = (GenericUser)q.getSingleResult();
    		GenericUser u2 = (GenericUser)q2.getSingleResult();
    		Friendship friendship = new Friendship(u1, u2, type); 
    		em.persist(friendship);
    	}
    	catch(Exception e){
    		System.out.println("Set della domanda di amicizia fallito");
    	}
    	
		
		
	}

    @Override
	public void confirmFriendship(GenericUser User1, GenericUser User2) {
		
		Query q = em.createQuery("SELECT f FROM Friendship f WHERE ((f.friend1 = :user1 AND f.friend2 = :user2) OR (f.friend1 = :user2 AND f.friend2 = :user1))");
		q.setParameter("friend1", User1);
		q.setParameter("friend2", User2);
		
		try{
			Friendship f = (Friendship)q.getSingleResult();
			f.setConfirmed(true);
			em.persist(f);
		}
		catch(Exception e){
			System.out.println("errore nella conferma dell'amicizia");
		}
	
    }

	@Override
	public List<GenericUser> getAllFriends(GenericUser u) {
		Query q = em.createQuery("SELECT f.friend2 FROM Friendship f WHERE f.friend1 = :friend1 AND f.confirmed = :confirmed");
		q.setParameter("friend1", u);
		q.setParameter("confirmed", true);
		Query q2 = em.createQuery("SELECT f.friend1 FROM Friendship f WHERE f.friend2 = :friend2 AND f.confirmed = :confirmed");
		q2.setParameter("friend2", u);
		q.setParameter("confirmed", true);
		
		return mergeQueryResults(q,q2);
	}

	

	@Override
	public List<GenericUser> getTypeABFriends(GenericUser u, boolean type) {
		Query q = em.createQuery("SELECT f.friend2 FROM Friendship f WHERE f.friend1 = :friend1 AND f.confirmed = :confirmed AND f.type = :type");
		q.setParameter("friend1", u);
		q.setParameter("type", type);
		q.setParameter("confirmed", true);
		Query q2 = em.createQuery("SELECT f.friend1 FROM Friendship f WHERE f.friend2 = :friend2 AND f.confirmed = :confirmed AND f.type = :type");
		q2.setParameter("friend1", u);
		q2.setParameter("type", type);
		q2.setParameter("confirmed", true);
		
		return mergeQueryResults(q,q2);
	}

	private List<GenericUser> mergeQueryResults(Query q, Query q2) {
		try{
			List<GenericUser> l1 = (List<GenericUser>)q.getResultList();
			List<GenericUser> l2 = (List<GenericUser>)q2.getResultList();
			List<GenericUser> result = new ArrayList<GenericUser>();
			return mergeLists(result, l1,l2);
		}
		catch(Exception e){
			return null;
		}
	}

	private List<GenericUser> mergeLists(List<GenericUser> result, List<GenericUser> l1, List<GenericUser> l2) {
		result.addAll(l1);
		result.addAll(l2);
		
		Set<GenericUser> resultSet = new HashSet<GenericUser>(result);
		return new ArrayList<GenericUser>(resultSet);
	}

}
