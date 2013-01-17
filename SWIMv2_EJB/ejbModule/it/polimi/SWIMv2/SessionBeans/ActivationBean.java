package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.AlreadyValidatedUserException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 * Session Bean implementation class ActivationBean
 */
@Stateless
public class ActivationBean implements ActivationBeanLocal {

	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ActivationBean() {
        // TODO Auto-generated constructor stub
    }
    
    public GenericUser activateAccount(String email, String validation) throws AlreadyValidatedUserException{
    	GenericUser u = getUserFromEmail(email);
    	if(u.isConfirmed()){
    		throw new AlreadyValidatedUserException();
    	}
    	if(u!=null && u.getRegistrationCode().equals(validation)){
    		u.setConfirmed(true);
    		System.out.println("utente validato per il login");
    		return u;
    	}
    	
    	return null;
    }
    
    @Override
	public GenericUser getUserFromEmail(String email){
	
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
		q.setParameter("email", email);
    try{
    	GenericUser u = (GenericUser) q.getSingleResult();
    	System.out.println("query riuscita");
        return u;
    }
    catch(Exception e){
    	System.out.println("query fallita");
    	return null;
    }
    
    }
}
