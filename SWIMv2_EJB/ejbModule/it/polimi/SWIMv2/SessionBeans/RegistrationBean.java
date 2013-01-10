package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.User;

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
    
    @Override
    public void insertIntoDatabase(String firstName, String lastName, String email, String password, String city){
    	
    	
    	
    	User u = new User(firstName, lastName, email, password, city, false);
    	em.persist(u);
    	
    	System.out.println("Sono arrivato in questo punto");
    }
    

}
