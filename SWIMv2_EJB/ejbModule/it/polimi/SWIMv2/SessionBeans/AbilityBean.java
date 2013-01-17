package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Ability;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class AbilityBean
 */
@Stateless
public class AbilityBean implements AbilityBeanLocal {

	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager entityManager;
	
    /**
     * Default constructor. 
     */
    public AbilityBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void addAbilityToUser(String userEmail, int abilityId) {
		
		try {
			
			  Query abilityQuery = entityManager.createQuery(" SELECT a FROM Ability a WHERE a.id = :abilityId");
			  abilityQuery.setParameter("abilityId", abilityId);
			  Query userQuery = entityManager.createQuery(" SELECT u FROM User u WHERE u.email = :userEmail");
			  abilityQuery.setParameter("userEmail", userEmail);
			  
			  
			  if(abilityQuery.getSingleResult() != null && userQuery.getSingleResult() != null){
			  
				  Ability ability = (Ability) abilityQuery.getSingleResult(); 
			      GenericUser user = (GenericUser) userQuery.getSingleResult();
			  
			  
			      user.addAbility(ability);
			      entityManager.persist(user);
			      
			  }
			
			} catch (EntityNotFoundException exc) {}
		      catch (NonUniqueResultException exc) {}
		      
	    }

	@Override
	public void createAbility(String name, String description, String creatorEmail) {
		
		try {
			
			  Ability newAbility = new Ability(name,description);
			  
			  
			     
			     // entityManager.persist();
			      
			  
			
			} catch (EntityNotFoundException exc) {}
		      catch (NonUniqueResultException exc) {}
		
	}

	
		
		
		
		


}
