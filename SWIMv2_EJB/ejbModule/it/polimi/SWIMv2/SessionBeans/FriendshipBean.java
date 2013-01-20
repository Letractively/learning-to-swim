package it.polimi.SWIMv2.SessionBeans;


import it.polimi.SWIMv2.EntityBeans.Friendship;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

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
	
    /**
     * Default constructor. 
     */
    public FriendshipBean() {
        // TODO Auto-generated constructor stub
    }

    @Override
	public void friendshipRequest(String userEmail1, String userEmail2, boolean direct) {

    	Query friend1Query = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :email");
		friend1Query.setParameter("email", userEmail1);
		  
		GenericUser user1 = (GenericUser) friend1Query.getSingleResult();
		
		Query friend2Query = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :email");
		friend2Query.setParameter("email", userEmail2);
		  
		GenericUser user2 = (GenericUser) friend2Query.getSingleResult();

		
		Friendship friendship = new Friendship(user1, user2, direct); 
		entityManager.persist(friendship);
		
	}

    @Override
	public void confirmationFriendship(Long userId1, Long userId2) {
		
		//QUERY DA TESTARE,RIGUARDARE
		Query friendshipQuery = entityManager.createQuery("SELECT f FROM Friendship f WHERE f.friendship.getFriend1() = :Id1 AND f.friendship.getFriend2() = :Id2");
		friendshipQuery.setParameter("Id1", userId1);
		friendshipQuery.setParameter("Id2", userId2);

		
		Friendship friendship = (Friendship)friendshipQuery.getSingleResult();
		boolean confirmation = true;
		
		friendship.setConfirmation(confirmation);
		entityManager.persist(friendship);
		
	
    }



}
