package it.polimi.SWIMv2.SessionBeans;

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
		
			  Query receiverQuery = entityManager.createQuery(" SELECT user FROM User user WHERE user.email = 'receiverEmail' ");
			  
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
		
		Query senderIdQuery =  entityManager.createQuery("SELECT ID FROM User sender WHERE sender.email='senderEmail'");
		Long senderId = (Long)senderIdQuery.getSingleResult();
		
		Query messageIdQuery = entityManager.createQuery("SELECT MAX(ID) FROM Message message WHERE message.Sender='senderId'");
		Long messageId = (Long)messageIdQuery.getSingleResult();
		
		Query receiverIdQuery =  entityManager.createQuery("SELECT ID FROM User receiver WHERE receiver.email='receiverEmail'");
		Long receiverId = (Long)receiverIdQuery.getSingleResult();
		
		Message message = new Message(messageId, senderId, receiverId, body);
		
		try{
    		 entityManager.persist(message);
    	}
    	catch(Exception e){}
    
	}
	
	

}
