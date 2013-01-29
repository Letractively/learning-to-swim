package it.polimi.SWIMv2.SessionBeans;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.Exceptions.IllegalEmailException;
import it.polimi.SWIMv2.Utilities.ActivationByMail;
import it.polimi.SWIMv2.Utilities.EmailParser;
import it.polimi.SWIMv2.Utilities.PasswordHash;

import javax.ejb.Stateless;
import javax.persistence.*;


/**
 * Session Bean implementation class RegistrationBean
 * 
 * Questa classe si occupa di gestire la logica di business della registrazione: verifica il fatto che l'email sia ben formata, che non ne esista già
 * una uguale nel database e crea l'hash salato della password
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
    public void insertIntoDatabase(String firstName, String lastName, String email, String password, String city) throws IllegalEmailException{
    	
    	ActivationByMail abm = new ActivationByMail();
    	EmailParser eP = new EmailParser(email);
		Map<String,String> activatingMailParameters = new HashMap<String,String>();
		
		if(!eP.parseEmail()){
    		throw new IllegalEmailException();
    	}
		
    	PasswordHash ph = new PasswordHash();
    	
    	try {
			String hash = ph.createHash(password);
			System.out.println(hash);
			User u = new User(firstName, lastName, email, hash, city, false);
			
    		//TODO rimuovere la println
    		System.out.println("non esiste tale utente e quindi posso crearlo");
    		em.persist(u);
	    	
    		String randomCode = randomString(30);
    		String activationLink = "https://localhost:8443/SWIMv2_DWP/confirm?activationcode=" + randomCode + "&user=" + email; 
    		
    		activatingMailParameters = generateActivationMailParameters(activatingMailParameters, email, firstName, lastName, activationLink, randomCode);
    		
    		abm.sendMsg(activatingMailParameters.get("to"), activatingMailParameters.get("from"), activatingMailParameters.get("subject"), activatingMailParameters.get("body"));
    			
    		System.out.println("ho inviato correttamente la mail");
    		
    		u.setRegistrationCode(randomCode);
    		u.setConfirmed(false);
    		//TODO va fatto dopo ogni modifica o si può togliere?
    		em.persist(u);
	    	
			
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch(Exception e){
    		//TODO rimuovere la println e, se vogliamo essere precisi, sostituire a "Exception" il nome della sua sottoclasse che corrisponde all'eccezione lanciata
    		e.printStackTrace();
			System.out.println("l'email esiste già: l'utente non viene creato");
    	}
    }
    
    @Override
    public boolean isEmailAvailable(String email){
    	
    	try{
    		Query q = em.createQuery("SELECT u.email FROM GenericUser u WHERE u.email = :email");
        	q.setParameter("email", email);
        	
        	String e = (String)q.getSingleResult();
        	
        	return false;
    	}
    	catch(Exception e){
    		return true;
    	}
    	
    	
    	
    }


	private Map<String,String> generateActivationMailParameters(Map<String,String> activatingMailParameters, String email, String nome, String cognome, String activationLink, String randomCode) {
		
		
		//TODO ricordarsi di mettere il parametro email al posto della mia mail
		activatingMailParameters.put("to", /*"e.manuele.uliana.90@gmail.com"*/email);
		activatingMailParameters.put("from", "activation@SWIMv2.com");
		activatingMailParameters.put("subject", "Activate your SWIMv2 profile");
		activatingMailParameters.put("body", "Per attivare il tuo profilo clicca sul seguente link:\n <a href='" + activationLink + "'>" + activationLink + "</a>");
		
		return activatingMailParameters;
	}
	
	private String randomString (int length) {
	    Random rnd = new Random();
	    char[] arr = new char[length];

	    for (int i=0; i<length; i++) {
	        int n = rnd.nextInt (36);
	        arr[i] = (char) (n < 10 ? '0'+n : 'a'+n-10);
	    }

	    return new String (arr);
	}
}
