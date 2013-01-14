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
	public List<GenericUser> searchForUsers(String f, String l, String c, String a) throws VoidSearchException{
		List<GenericUser> result;
		//System.out.println("Almeno in questo metodo sono entrato");
		if(f!= null && l != null && c != null && a != null){
			result = fullSearch(f,l,c,a);
		}
		else if(f!= null && l != null && c != null && a == null){
			result = SearchByNameSurnameCity(f,l,c);
		}
		else if(f!= null && l != null && c == null && a != null){
			result = SearchByNameSurnameAbility(f,l,a);
		}
		else if(f!= null && l == null && c != null && a != null){
			result = SearchByNameCityAbility(f,c,a);
		}
		else if(f== null && l != null && c != null && a != null){
			result = SearchBySurnameCityAbility(l,c,a);
		}
		else if(f!= null && l != null && c == null && a == null){
			result = SearchByNameSurname(f,l);
		}
		else if(f!= null && l == null && c != null && a == null){
			result = SearchByNameCity(f,c);
		}
		else if(f== null && l != null && c != null && a == null){
			result = SearchBySurnameCity(l,c);
		}
		else if(f!= null && l == null && c == null && a != null){
			result = SearchByNameAbility(f,a);
		}
		else if(f== null && l != null && c == null && a != null){
			result = SearchBySurnameAbility(l,a);
		}
		else if(f== null && l == null && c != null && a != null){
			result = SearchByCityAbility(c,a);
		}
		else if(f!= null && l == null && c == null && a == null){
			result = SearchByName(f);
		}
		else if(f== null && l != null && c == null && a == null){
			result = SearchBySurname(l);
		}
		else if(f== null && l == null && c != null && a == null){
			result = SearchByCity(c);
		}
		else if(f== null && l == null && c == null && a != null){
			result = SearchByAbility(a);
		}
		else if(f== null && l == null && c == null && a == null){
			throw new VoidSearchException();
		}
		else{
			result = null;
		}
		//System.out.println("Sono arrivato qui e result è" + result.toString());
		return result;
		
	}

	private List<GenericUser> SearchByAbility(String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE  :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByCity(String c) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.city = :city");
		
		q.setParameter("city", c);;
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchBySurname(String l) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.lastName = :lastName");
		
		q.setParameter("lastName", l);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByName(String f) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName");
		
		q.setParameter("firstName", f);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByCityAbility(String c, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.city = :city AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("city", c);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchBySurnameAbility(String l, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.lastName = :lastName AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("lastName", l);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByNameAbility(String f, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("firstName", f);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchBySurnameCity(String l, String c) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.lastName = :lastName AND u.city = :city");
		
		q.setParameter("lastName", l);
		q.setParameter("city", c);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByNameCity(String f, String c) {
Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.city = :city");
		
		q.setParameter("firstName", f);
		q.setParameter("city", c);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByNameSurname(String f, String l) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.lastName = :lastName");
		
		q.setParameter("firstName", f);
		q.setParameter("lastName", l);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchBySurnameCityAbility(String l, String c, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.lastName = :lastName AND u.city = :city AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("lastName", l);
		q.setParameter("city", c);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByNameCityAbility(String f, String c, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.city = :city AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("firstName", f);
		q.setParameter("city", c);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
		
	}

	private List<GenericUser> SearchByNameSurnameAbility(String f, String l, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.lastName = :lastName AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("firstName", f);
		q.setParameter("lastName", l);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
	}

	private List<GenericUser> SearchByNameSurnameCity(String f, String l, String c) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.lastName = :lastName AND u.city = :city");
		q.setParameter("firstName", f);
		q.setParameter("lastName", l);
		q.setParameter("city", c);
		
		return (ArrayList<GenericUser>)q.getResultList();
	}

	private List<GenericUser> fullSearch(String f, String l, String c, String a) {
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.firstName = :firstName AND u.lastName = :lastName AND u.city = :city AND :ability IN (SELECT a.name FROM Ability a WHERE a IN u.abilities)");
		
		q.setParameter("firstName", f);
		q.setParameter("lastName", l);
		q.setParameter("city", c);
		q.setParameter("ability", a);
		
		return (ArrayList<GenericUser>)q.getResultList();
	}

}
