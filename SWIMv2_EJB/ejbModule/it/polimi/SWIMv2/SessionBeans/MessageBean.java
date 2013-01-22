package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.Message;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


/**
 * Session Bean implementation class MessageBean
 */
@Stateless
public class MessageBean implements MessageBeanLocal {

	
	@PersistenceContext(unitName = "SWIMv2_PU")
	private EntityManager entityManager;
	
    
    @Override
	public boolean validReceiver(String receiverEmail) {
		
		try {
		
			  Query receiverQuery = entityManager.createQuery(" SELECT user FROM GenericUser user WHERE user.email = :receiverEmail ");
			  receiverQuery.setParameter("receiverEmail", receiverEmail);
			  
			  if(receiverQuery.getSingleResult() != null){
			  return true;
			  }
			
			} catch (EntityNotFoundException exc) {
				System.out.println("La mail inserita non e' corretta");
				
			}
		      catch (NonUniqueResultException exc) {}
		      return false;
	    }

	
    @Override
	public void insertToDatabase(String senderEmail, String receiverEmail, String body) {
		
    	try{
		
    		Query senderQuery =  entityManager.createQuery("SELECT sender FROM GenericUser sender WHERE sender.email= :senderEmail");
			senderQuery.setParameter("senderEmail", senderEmail);
			GenericUser sender = (GenericUser)senderQuery.getSingleResult();
			
			Query messageIdQuery = entityManager.createQuery("SELECT MAX(message.id) FROM Message message WHERE message.sender.email= :senderEmail");
			messageIdQuery.setParameter("senderEmail", senderEmail);
			Long messageId = (Long)(messageIdQuery.getSingleResult()) +1;
			
			Query receiverQuery =  entityManager.createQuery("SELECT receiver FROM GenericUser receiver WHERE receiver.email= :receiverEmail");
			receiverQuery.setParameter("receiverEmail", receiverEmail);
			GenericUser receiver = (GenericUser)receiverQuery.getSingleResult();
			
			Message message = new Message(messageId, sender, receiver, body);
			
			entityManager.persist(message);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    
	}
	
	

}
