package it.polimi.SWIMv2.SessionBeans;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.User;
import it.polimi.SWIMv2.Exceptions.IllegalEmailException;
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
    public void insertIntoDatabase(String firstName, String lastName, String email, String password, String city) throws IllegalEmailException{
    	
    	/*if(!emailCorrectness(email)){
    		throw new IllegalEmailException();
    	}*/
    	
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
    
    
    public boolean emailCorrectness(String email){
    	
    	String mail = email;
    	
    	int i,j,k;
    	
    	i = mail.indexOf('@');
    	j = mail.lastIndexOf('@');
    	k = mail.lastIndexOf('.');
    	
    	/**
    	 * i == -1 se e solo se la chiocciola non esiste
    	 */
    	if(i == -1){
    		return false;
    	}
    	
    	/**
    	 * i != j se e solo se esistono più chiocciole
    	 */
    	if(i != j){
    		return false;
    	}
    	
    	/**
    	 * l'ultimo carattere '.' di una mail deve trovarsi dopo la chiocciola e tra di loro ci deve essere almeno un altro carattere
    	 */
    	if(k >= j+1){
    		return false;
    	}
    	
    	/**
    	 * la chiocciola non può essere il primo carattere
    	 */
    	if(i == 1){
    		return false;
    	}
    	
    	int extension = mail.length() - k;
    	
    	/**
    	 * l'ultimo punto deve essere seguito da almeno due caratteri che non siano un punto
    	 */
    	if(extension < 2){
    		return false;
    	}
    	
    	Set<Character> allowedCharacters = new HashSet<Character>();
    	Set<Character> allowedCharacters2 = new HashSet<Character>();
    	Set<Character> allowedCharacters3 = new HashSet<Character>();
    	Set<Character> allowedCharacters4 = new HashSet<Character>();
    	
    	fillAllowedCharacters(allowedCharacters);
    	fillAllowedCharacters2(allowedCharacters2);
    	fillAllowedCharacters3(allowedCharacters3);
    	fillAllowedCharacters4(allowedCharacters4);
    	
    	char [] mailArray = mail.toCharArray();
    	
    	/**
    	 * dopo l'ultimo punto possono solo esserci maiuscole o minuscole
    	 */
    	for(int index = mail.length(); index > k; index--){
    		//1 2 3 4 5 6 7
    		//a b @ c . i t
    		//0 1 2 3 4 5 6
    		//a b @ c . i t
    		//i=j=3
    		//k=5
    		//length = 7
    		//extension = 2
    		//for(int index = 7; index > 5; index --)
    		if(!controlExtension(mailArray[index-1],allowedCharacters,allowedCharacters2)){
    			return false;
    		}
    	}
    	
    	/**
    	 * il primo carattere può essere solo una lettera maiuscola/minuscola o un numero
    	 */
    	if(!controlFirstCharacter(mailArray[0],allowedCharacters,allowedCharacters2,allowedCharacters3)){
    		return false;
    	}
    	
    	/**
    	 * dal secondo carattere in poi, fino alla chiocciola, ci possono essere solo i caratteri contenuti nei vari set di caratteri permessi
    	 */
    	if(i > 2){
    		for(int index = 1; index < i-1; i++){
    			if(!controlNameAndProvider(mailArray[index],allowedCharacters,allowedCharacters2,allowedCharacters3,allowedCharacters4)){
        			return false;
        		}
    		}
    	}
    	
    	/**
    	 * il provider del servizio mail può avere solo i caratteri contenuti nei vari set di caratteri permessi
    	 */
    	for(int index = i+1; index < k-1; index++){
    		if(!controlNameAndProvider(mailArray[index],allowedCharacters,allowedCharacters2,allowedCharacters3,allowedCharacters4)){
    			return false;
    		}
    	}
    	
    	/**
    	 * se la stringa ha passato tutti i test, allora è ben formata
    	 */
    	return true;
    
    }


   
	private boolean controlNameAndProvider(char c, Set<Character> allowedCharacters, Set<Character> allowedCharacters2, Set<Character> allowedCharacters3, Set<Character> allowedCharacters4) {
		
		List<Character> ac = new ArrayList<Character>(allowedCharacters);
		List<Character> ac2 = new ArrayList<Character>(allowedCharacters2);
		List<Character> ac3 = new ArrayList<Character>(allowedCharacters3);
		List<Character> ac4 = new ArrayList<Character>(allowedCharacters4);
		Character temp = new Character(c);
		
		for(int i=0; i< ac.size(); i++){
			
			if(temp == ac.get(i)){
				return true;
			}
		}
		
		for(int i=0; i< ac2.size(); i++){
				
			if(temp == ac2.get(i)){
				return true;
			}
			
		}
		
		for(int i=0; i< ac3.size(); i++){
			
			if(temp == ac3.get(i)){
				return true;
			}
			
		}
		
		for(int i=0; i< ac4.size(); i++){
			
			if(temp == ac4.get(i)){
				return true;
			}
			
		}
		
		return false;
		
	}


	private boolean controlFirstCharacter(char c, Set<Character> allowedCharacters, Set<Character> allowedCharacters2, Set<Character> allowedCharacters3) {
		List<Character> ac = new ArrayList<Character>(allowedCharacters);
		List<Character> ac2 = new ArrayList<Character>(allowedCharacters2);
		List<Character> ac3 = new ArrayList<Character>(allowedCharacters3);
		Character temp = new Character(c);
		
		for(int i=0; i< ac.size(); i++){
			
			if(temp == ac.get(i)){
				return true;
			}
		}
		
		for(int i=0; i< ac2.size(); i++){
				
			if(temp == ac2.get(i)){
				return true;
			}
			
		}
		
		for(int i=0; i< ac3.size(); i++){
			
			if(temp == ac3.get(i)){
				return true;
			}
			
		}
		
		return false;
		
	}


	private boolean controlExtension(char mailArray, Set<Character> allowedCharacters, Set<Character> allowedCharacters2) {
		
		List<Character> ac = new ArrayList<Character>(allowedCharacters);
		List<Character> ac2 = new ArrayList<Character>(allowedCharacters2);
		Character temp = new Character(mailArray);
		
		for(int i=0; i< ac.size(); i++){
		
			if(temp == ac.get(i)){
				return true;
			}
		}
		
		for(int i=0; i< ac2.size(); i++){
				
			if(temp == ac2.get(i)){
				return true;
			}
			
		}
		
		return false;
		
	}


	private void fillAllowedCharacters4(Set<Character> allowedCharacters4) {
		
		allowedCharacters4.add('.');
		allowedCharacters4.add('-');
		allowedCharacters4.add('_');
		
	}


	private void fillAllowedCharacters3(Set<Character> allowedCharacters3) {
		
		allowedCharacters3.add('0');
		allowedCharacters3.add('1');
		allowedCharacters3.add('2');
		allowedCharacters3.add('3');
		allowedCharacters3.add('4');
		allowedCharacters3.add('5');
		allowedCharacters3.add('6');
		allowedCharacters3.add('7');
		allowedCharacters3.add('8');
		allowedCharacters3.add('9');
		
	}


	private void fillAllowedCharacters2(Set<Character> allowedCharacters2) {
		
		allowedCharacters2.add('A');
		allowedCharacters2.add('B');
		allowedCharacters2.add('C');
		allowedCharacters2.add('D');
		allowedCharacters2.add('E');
		allowedCharacters2.add('F');
		allowedCharacters2.add('G');
		allowedCharacters2.add('H');
		allowedCharacters2.add('I');
		allowedCharacters2.add('J');
		allowedCharacters2.add('K');
		allowedCharacters2.add('L');
		allowedCharacters2.add('M');
		allowedCharacters2.add('N');
		allowedCharacters2.add('O');
		allowedCharacters2.add('P');
		allowedCharacters2.add('Q');
		allowedCharacters2.add('R');
		allowedCharacters2.add('S');
		allowedCharacters2.add('T');
		allowedCharacters2.add('U');
		allowedCharacters2.add('V');
		allowedCharacters2.add('W');
		allowedCharacters2.add('X');
		allowedCharacters2.add('Y');
		allowedCharacters2.add('Z');
		
	}


	private void fillAllowedCharacters(Set<Character> allowedCharacters) {
		
		allowedCharacters.add('a');
		allowedCharacters.add('b');
		allowedCharacters.add('c');
		allowedCharacters.add('d');
		allowedCharacters.add('e');
		allowedCharacters.add('f');
		allowedCharacters.add('g');
		allowedCharacters.add('h');
		allowedCharacters.add('i');
		allowedCharacters.add('j');
		allowedCharacters.add('k');
		allowedCharacters.add('l');
		allowedCharacters.add('m');
		allowedCharacters.add('n');
		allowedCharacters.add('o');
		allowedCharacters.add('p');
		allowedCharacters.add('q');
		allowedCharacters.add('r');
		allowedCharacters.add('s');
		allowedCharacters.add('t');
		allowedCharacters.add('u');
		allowedCharacters.add('v');
		allowedCharacters.add('w');
		allowedCharacters.add('x');
		allowedCharacters.add('y');
		allowedCharacters.add('z');
		
	}
    

}
