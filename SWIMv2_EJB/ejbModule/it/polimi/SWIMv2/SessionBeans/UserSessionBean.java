package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class UserSessionBean
 */
@Stateless
public class UserSessionBean implements UserSessionBeanLocal {

    /**
     * Default constructor. 
     */
    public UserSessionBean() {
        // TODO Auto-generated constructor stub
    }
    
    private String nome;
	private String cognome;
    private String email;
    private String city;
    private List<Integer> Feedback = new ArrayList<Integer>();
    
    @PersistenceContext(unitName = "SWIMv2_PU")
    private EntityManager em;
    
    
    public String getNome() {
 		return nome;
 	}
 	public void setNome(String nome) {
 		this.nome = nome;
 	}
 	public String getCognome() {
 		return cognome;
 	}
 	public void setCognome(String cognome) {
 		this.cognome = cognome;
 	}
 	public String getEmail() {
 		return email;
 	}
 	public void setEmail(String email) {
 		this.email = email;
 	}
 	public String getCity() {
 		return city;
 	}
 	public void setCity(String city) {
 		this.city = city;
 	}
 	public List<Integer> getFeedback() {
 		return Feedback;
 	}
 	public void setFeedback(List<Integer> feedback) {
 		Feedback = feedback;
 	}
 	
 	@Override
 	public GenericUser getDataFromDatabase(String email){
 		
 		Query q = em.createQuery("SELECT u FROM GenericUser u WHERE u.email = :email");
 		q.setParameter("email", email);
 		
 		return (GenericUser)q.getSingleResult();
 	}
}
