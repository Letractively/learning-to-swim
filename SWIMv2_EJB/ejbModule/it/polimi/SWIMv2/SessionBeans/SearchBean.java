package it.polimi.SWIMv2.SessionBeans;

import java.util.ArrayList;
import java.util.List;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.VoidSearchException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class SearchBean
 */
@Stateless
public class SearchBean implements SearchBeanLocal {

	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public SearchBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<GenericUser> searchByName(String firstName, String lastName){
		List<GenericUser> result;
		
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :name OR u.lastName = :surname");
		q.setParameter("name", firstName);
		q.setParameter("surname", lastName);
		
		try{
			result = (List<GenericUser>)q.getResultList();
			return result;
		}
		catch(Exception e){
			System.out.println("non esistono utenti con tale anagrafica");
			return null;
		}
	}
	
	
	public List<GenericUser> searchByAbility(String ability){
		List<GenericUser> userList;
		
		Query q = em.createQuery("SELECT u FROM UserAbilities ua, GenericUser u, Ability a WHERE u.id = ua.userAbilitiesKey.user AND a.id = ua.userAbilitiesKey.ability AND a.name = :ability");
		q.setParameter("ability", ability);
		try{
			userList = (List<GenericUser>)q.getResultList();
			return userList;
		}
		catch(Exception e){
			System.out.println("non esistono utenti con tale abilità");
			return null;
		}
		
	}



}
