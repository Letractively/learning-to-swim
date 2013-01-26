package it.polimi.SWIMv2.SessionBeans;

import java.util.ArrayList;
import java.util.List;

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
	public boolean validAbility(String abilityName) {
    	try {
    		Query validAbilityQuery = entityManager.createQuery(" SELECT a FROM Ability a WHERE a.name = :name");
    		validAbilityQuery.setParameter("name", abilityName);
    		
    		validAbilityQuery.executeUpdate();
    		return false; 

    	} catch (Exception exc) {
    		return true;
    	}			  
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
		}
		catch (EntityNotFoundException exc) {
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
	public void deleteAllUserAbilities(String userEmail) {
		try {
			Query userAbilityQuery = entityManager.createQuery("DELETE userab FROM UserAbilities userab WHERE userab.userAbilitiesKey.user.email= :email");
			userAbilityQuery.setParameter("email", userEmail);
			
			userAbilityQuery.executeUpdate();
			
			entityManager.getTransaction().commit();
		}
		catch (EntityNotFoundException exc) {
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
	public List<String> returnAbilityToAdd(String userEmail) {
		
		try {
			  List<Ability> totalAbilities = returnAbilityList(); 
			  List<Ability> userAbilities = returnUserAbilityList(userEmail);
			  List<Ability> abilities = returnSubAbilitiesList(totalAbilities, userAbilities);
			  return returnLineListAbility(abilities);
		} catch (EntityNotFoundException exc) {
			List<Ability> totalAbilities = returnAbilityList();
			return returnLineListAbility(totalAbilities);
		} catch (Exception exc) {
			exc.printStackTrace();
			List<Ability> totalAbilities = returnAbilityList();
			return returnLineListAbility(totalAbilities);
		}         
    }

	@Override
	public List<String> returnUserAbilities(String userEmail) {
		List<Ability> userAbilities = returnUserAbilityList(userEmail);
		return returnLineListAbility(userAbilities);
	}
	
	@Override
	public List<String> returnTotalAbilityList() {
		
		List<Ability> abilitiesList = returnAbilityList();
		return returnLineListAbility(abilitiesList);
	
	}

	private List<Ability> returnUserAbilityList(String userEmail){
		Query userAbilityQuery = entityManager.createQuery("SELECT userab.userAbilitiesKey.ability FROM UserAbilities userab WHERE userab.userAbilitiesKey.user.email= :email");
		userAbilityQuery.setParameter("email", userEmail);
		return new ArrayList<Ability>((List<Ability>)userAbilityQuery.getResultList());
	}	
		
	private List<Ability> returnAbilityList(){
		Query totalAbilityQuery = entityManager.createQuery(" SELECT a FROM Ability a ");
		return new ArrayList<Ability>((List<Ability>)totalAbilityQuery.getResultList());
	}
	
	private List<String> returnLineListAbility(List<Ability> abilities){
		List<String> abilitiesList = new ArrayList<String>();
		for(Ability ability: abilities){
			
			String id = new String(ability.getId() + "\t" );
		    String name = new String(ability.getName());
			
			
			String lineAbility = new String(id + name);
			abilitiesList.add(lineAbility);		
		}
		return abilitiesList;
	}
	
	private List<Ability> returnSubAbilitiesList(List<Ability> totalAbilities, List<Ability> userAbilities) {
		List<Ability> subAbilitiesList = new ArrayList<Ability>(totalAbilities);
		for(Ability ta: totalAbilities){
			for(Ability ua: userAbilities){
				if(ta.getName().equals(ua.getName())){
					subAbilitiesList.remove(ta);
					break;				
				}
			}
		}
		return subAbilitiesList;
	}
}
