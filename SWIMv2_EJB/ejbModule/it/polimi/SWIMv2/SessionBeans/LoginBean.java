package it.polimi.SWIMv2.SessionBeans;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.UserNotFoundException;
import it.polimi.SWIMv2.Utilities.PasswordHash;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
public class LoginBean implements LoginBeanLocal {

	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager em;
	private PasswordHash ph = new PasswordHash();
	
	
    /**
     * Default constructor. 
     */
    public LoginBean() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Il metodo prima verifica l'esistenza nel database di un utente con l'email uguale a quella ricevuta, poi verifica se la sua password �� giusta 
     */
	@Override
	public GenericUser validateUser(String email, String password){
	
		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
		q.setParameter("email", email);
		//Query q = em.createNativeQuery("SELECT * FROM User WHERE Email = 'emanuele.uliana.90@gmail.com'");
		
			
		
		
		
		
		/*if(u == null){
			throw new UserNotFoundException();
		}*/
		
		
		try {
			GenericUser u = (GenericUser)q.getSingleResult();
			String pwd = password; //sembra ridondante, ma �� per evitare che un parametro venga modificato
			if(ph.validatePassword(pwd, u.getPassword())){
				return u;
			}
			
		} catch (NoSuchAlgorithmException e) {
			// TODO togliere
			e.printStackTrace();
			return null;
		} catch (InvalidKeySpecException e) {
			// TODO togliere
			e.printStackTrace();
			return null;
		} catch (Exception e){
			return null;
		}
		
		
		
		return null;
	}

}
