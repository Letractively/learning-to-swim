package it.polimi.SWIMv2.SessionBeans;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import it.polimi.SWIMv2.Utilities.PasswordHash;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
public class LoginBean implements LoginBeanLocal {

	private PasswordHash ph = new PasswordHash();
	
	
    /**
     * Default constructor. 
     */
    public LoginBean() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public boolean validateUser(String email, String password) {
		//TODO controllare l'esistenza dell'email e, in caso contrario, lanciare un'eccezione
		
		
		try {
			String pwd = password; //sembra ridondante, ma è per evitare che un parametro venga modificato
			String hashedPassword = ph.createHash(pwd);
			return ph.validatePassword(pwd, hashedPassword);
			
		} catch (NoSuchAlgorithmException e) {
			// TODO togliere
			e.printStackTrace();
			return false;
		} catch (InvalidKeySpecException e) {
			// TODO togliere
			e.printStackTrace();
			return false;
		}
	}

}
