package it.polimi.SWIMv2.SessionBeans;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.Utilities.PasswordHash;

import javax.ejb.Stateless;
import javax.persistence.*;

/**
 * Session Bean implementation class RegistrationBean
 */
@Stateless
public class RegistrationBean implements RegistrationBeanLocal {

    /**
     * Default constructor. 
     */
    public RegistrationBean() {
        // TODO Auto-generated constructor stub
    }
    
    @PersistenceContext(unitName = "SWIMv2_PU")
    private EntityManager em;
    
    
    /**
     * Inserisce l'utente nel database se non esiste già, nel quale caso...
     */
    @Override
    public void insertIntoDatabase(String firstName, String lastName, String email, String password, String city){
    	
    	PasswordHash ph = new PasswordHash();
    	
    	try {
			String hash = ph.createHash(password);
			System.out.println(hash);
			User u = new User(firstName, lastName, email, hash, city, false);
			try{
	    		//TODO rimuovere la println
	    		System.out.println("non esiste tale utente");
	    		em.persist(u);
	    	}
	    	catch(Exception e){
	    		//TODO rimuovere la println e, se vogliamo essere precisi, sostituire a "Exception" il nome della sua sottoclasse che corrisponde all'eccezione lanciata
	    		System.out.println("l'email esiste già");
	    	}
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    	
    	
    	
    	//TODO rimuovere la println
    	System.out.println("Sono arrivato in questo punto");
    }
    

}