package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Admin;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class UserSessionBean
 */
@Stateless
public class FriendProfileSessionBean implements FriendProfileSessionBeanLocal {

    /**
     * Default constructor. 
     */
    public FriendProfileSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "SWIMv2_PU")
    private EntityManager em;
    
 	
 	@Override
 	public String getFriendData(String email){
 		
 		try{
 			Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
  	 		q.setParameter("email", email);
 	 		
 	 		return friendDetails(q);
 		}
 		catch(Exception e){
 			e.printStackTrace();
 			return null;
 		}
 		
 	}


	private String friendDetails(Query q) {
		GenericUser friend = (GenericUser)q.getSingleResult();
		
		String friendEmail = friend.getEmail();
		Double avg = friend.getFeedbackAverage() + 1;
		String type = new String();
		
		if(friend instanceof User){
			type = "User";
		}
		else if(friend instanceof Admin){
			type = "Admin";
		}
		System.out.println("La media e' " + avg);
		return new String(friend.getFirstName() + "\t" + friend.getLastName() + "\t" + friendEmail + "\t" + friend.getCity() + "\t" + type + "\t" + avg);
	}
}
