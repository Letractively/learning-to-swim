package it.polimi.SWIMv2.SessionBeans;

import java.util.Map;

import it.polimi.SWIMv2.EntityBeans.Ability;
import it.polimi.SWIMv2.EntityBeans.Admin;
import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.UserAbilities;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


//TODO A CAUSA DEI CAMBIAMENTI NEL DATABASE TUTTI I METODI SONO DA RIPENSARE!!!


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
	public void addAbilityToUser(String userEmail, Long abilityId) {
		
		try {
			
			  Query abilityQuery = entityManager.createQuery(" SELECT a FROM Ability a WHERE a.id = :abilityId");
			  abilityQuery.setParameter("abilityId", abilityId);
			  Query userQuery = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :userEmail");
			  userQuery.setParameter("userEmail", userEmail);
			  
			  
			  Ability ability = (Ability) abilityQuery.getSingleResult(); 
			  GenericUser user = (GenericUser) userQuery.getSingleResult();
			  
			  
			  UserAbilities userAbilities = new UserAbilities(user, ability);
			  entityManager.persist(userAbilities);
			      
			  
			
			} catch (EntityNotFoundException exc) {
				exc.printStackTrace();
				}
		      catch (NonUniqueResultException exc) {
		    	  exc.printStackTrace();
		    	  }
			  catch (Exception exc) {
				  exc.printStackTrace();
				  }
		      
	    }

	@Override
	public void createAbility(String name, String description, String creatorEmail) {
		
		try {
			
			Query adminQuery = entityManager.createQuery(" SELECT a FROM Admin a WHERE a.email = :creatorEmail");
			adminQuery.setParameter("creatorEmail", creatorEmail);
			  
			Admin creator = (Admin) adminQuery.getSingleResult();
		    Ability newAbility = new Ability(name, description, creator);
			  
		    entityManager.persist(newAbility);
			      
			} catch (EntityNotFoundException exc) {}
		      catch (NonUniqueResultException exc) {}
		
	}

	
	@Override
	public Map<Ability,Boolean> getAbilitiesByUser(String userEmail) {
		/*Map<Ability,Boolean> mapAbility = null;
		try {
			Query abilityQuery = entityManager.createQuery(" SELECT a FROM Ability a");
			List<Ability> lstAbility = (List<Ability>)abilityQuery.getResultList();
			  
			Query userQuery = entityManager.createQuery(" SELECT u FROM GenericUser u WHERE u.email = :userEmail");
			userQuery.setParameter("userEmail", userEmail);
			
			GenericUser user = (GenericUser) userQuery.getSingleResult();
			Set<Ability> userAbilities= user.getAbilities();
			
			mapAbility = new HashedMap();
			
			for (Ability a : lstAbility) {
				mapAbility.put(a, false);
			}
			
			for (Ability a : userAbilities) {
				mapAbility.put(a, true);
			}
			
			return mapAbility;
		} catch (EntityNotFoundException exc) { exc.printStackTrace(); }
	      catch (NonUniqueResultException exc) { exc.printStackTrace();}*/
		return null;
	}		
		
		
		


}
