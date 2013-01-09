package it.polimi.SWIMv2.EntityBeans;
import javax.persistence.EntityManager;

/**
 * La classe che si occupa di interrogare il database con i dati provenienti dal form di login
 * @author emanuele
 *
 */
public class LoginController {

	private EntityManager em;
	
	/**
	 * Cerca nel database la tupla corrispondente a mail
	 * @param mail La stringa che è stata inserita nel form nel campo mail
	 * @param password La stringa che è stata inserita nel form nel campo password
	 * @return false se la tupla non esiste o la password corrispondente non è uguale a password, true altrimenti
	 */
	public boolean search(String mail, String password){
		User user = em.find(User.class,mail);
		
		if(user == null){
			return false;
		}
		else if(!user.getPassword().equals(password)){
			return false;
		}
		return true;
	}
	
	/**
	 * Cerca nel database la tupla corrispondente a mail
	 * @param mail Il campo univoco usato come chiave di ricerca
	 * @return false se la tupla non esiste
	 */
	//Questo overloading NON è inutile
	public boolean search(String mail){
		User user = em.find(User.class,mail);
		
		if(user == null){
			return false;
		}
		return true;
	}
	
	//prova
}
