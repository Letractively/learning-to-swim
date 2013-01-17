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
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FriendshipBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void setFriendshipRequested(GenericUser u1, GenericUser u2, boolean type) {
		//Long id1 = u1.getId();
		//Long id2 = u2.getId();
		
		Friendship f = new Friendship(u1,u2);
		f.setType(type);
		f.setConfirmation(false);
		em.persist(f);
	}

	@Override
	public void setFriendship(GenericUser u1, GenericUser u2) {
		
		Query q = em.createQuery("SELECT f FROM Friendship f WHERE f.friend1 = :f1 AND f.friend2 = f2");
		q.setParameter("f1", u1);
		q.setParameter("f2", u2);
		Friendship f = (Friendship)q.getSingleResult();
		f.setConfirmation(true);
		em.persist(f);
		
	}

}
