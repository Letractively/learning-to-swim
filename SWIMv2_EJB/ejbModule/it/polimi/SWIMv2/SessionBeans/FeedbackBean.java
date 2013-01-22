package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class FeedbackBean
 */
@Stateless
public class FeedbackBean implements FeedbackBeanLocal {

	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public FeedbackBean() {}

	@Override
	public boolean giveFeedback(String userEmail, Integer value) {
		
	   try {
			  Query userQuery = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :userEmail");
			  userQuery.setParameter("userEmail", userEmail);
			  GenericUser user = (GenericUser) userQuery.getSingleResult();
			
			  return assignFeedback(user,value);
			
			} catch (EntityNotFoundException exc) {
				exc.printStackTrace();
				return false;
				}
		      catch (NonUniqueResultException exc) {
		    	  exc.printStackTrace();
		    	  return false;
		    	  }
			  catch (Exception exc) {
				  exc.printStackTrace();
				  return false;
				  }
		
		
	}

	@Override
	public Double getFeedbackAverage(String userEmail) {
		
		try {
			  Query userQuery = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :userEmail");
			  userQuery.setParameter("userEmail", userEmail);
			  GenericUser user = (GenericUser) userQuery.getSingleResult();
			
			  return calculateAverage(user);
			
			} catch (EntityNotFoundException exc) {
				exc.printStackTrace();
				return null;
				}
		      catch (NonUniqueResultException exc) {
		    	  exc.printStackTrace();
		    	  return null;
		    	  }
			  catch (Exception exc) {
				  exc.printStackTrace();
				  return null;
				  }
		
	}

	private boolean assignFeedback(GenericUser user, Integer value) {
		
		int feedback = value.intValue();
		
		switch(feedback){
		    case 0:user.getFeedback().addZeroFeedback();
	           return true;
		    case 1:user.getFeedback().addOneFeedback();
		       return true;
		    case 2:user.getFeedback().addTwoFeedback();
		       return true;
	     	case 3:user.getFeedback().addThreeFeedback();
	           return true;
		    case 4:user.getFeedback().addFourFeedback();
		       return true;
		    default: return false;   
		}
	
	}
	
	private Double calculateAverage(GenericUser user) {
		
		Double zeroFeedback = (double) user.getFeedback().getZeroFeedback();
	    Double oneFeedback = (double) user.getFeedback().getOneFeedback();
		Double twoFeedback = (double) user.getFeedback().getTwoFeedback();
		Double threeFeedback = (double) user.getFeedback().getThreeFeedback();
		Double fourFeedback = (double) user.getFeedback().getFourFeedback();
		
		Double average = (zeroFeedback + oneFeedback + twoFeedback + threeFeedback + fourFeedback)/5;
		
		return average;
	
	}

}
