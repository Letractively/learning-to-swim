package it.polimi.SWIMv2.SessionBeans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.EntityBeans.Message;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
			  
			  receiverQuery.getSingleResult();
			  return true;
		} catch (NoResultException exc) {
				System.out.println("La mail inserita non e' corretta");
				return false;
		}
	}

	
    @Override
	public void insertToDatabase(String senderEmail, String receiverEmail, String body) {
		
    	try{
			Query messageIdQuery = entityManager.createQuery("SELECT max(message.messageKey.id) FROM Message message WHERE message.messageKey.sender.email= :senderEmail");
			messageIdQuery.setParameter("senderEmail", senderEmail);
			Long messageId = (Long)(messageIdQuery.getSingleResult())+1;
			
		    createAndPersistMessage(senderEmail, receiverEmail, body, messageId);
    	}
    	catch(NullPointerException e){
    		Long messageId = new Long(1);
    		createAndPersistMessage(senderEmail, receiverEmail, body, messageId);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    
	}
	
    @Override
	public List<String> returnReceivedMessages(String userEmail) {
    	List<String> lineMessages = new ArrayList<String>();
    	
		try{
			Query messageListQuery = entityManager.createQuery("SELECT message FROM Message message WHERE message.receiver.email= :email");
			messageListQuery.setParameter("email", userEmail);
			
			List<Message> messages = new ArrayList<Message>(messageListQuery.getResultList());
			
			lineMessages = returnLineListMessages(messages);
		    return lineMessages;			
    	}
    	catch(NullPointerException e){
    		e.printStackTrace();
    		return lineMessages;
        }
    	catch(Exception e){
    		e.printStackTrace();
    		return lineMessages;
    	}
	}

    @Override
	public List<String> returnSendedMessages(String userEmail) {
    	List<String> lineMessages = new ArrayList<String>();
    	
		try{
			Query messageListQuery = entityManager.createQuery("SELECT message FROM Message message WHERE message.messageKey.sender.email= :email");
			messageListQuery.setParameter("email", userEmail);
			
			List<Message> messages = new ArrayList<Message>(messageListQuery.getResultList());
			
			lineMessages = returnLineListMessages(messages);
		    return lineMessages;
    	}
    	catch(NullPointerException e){
    		e.printStackTrace();
    		return lineMessages;
        }
    	catch(Exception e){
    		e.printStackTrace();
    		return lineMessages;
    	}
    
	}
	
    private void createAndPersistMessage(String senderEmail, String receiverEmail, String body, Long messageId){
	    	
	    	Query senderQuery =  entityManager.createQuery("SELECT sender FROM GenericUser sender WHERE sender.email= :senderEmail");
			senderQuery.setParameter("senderEmail", senderEmail);
			GenericUser sender = (GenericUser)senderQuery.getSingleResult();
			
			Query receiverQuery =  entityManager.createQuery("SELECT receiver FROM GenericUser receiver WHERE receiver.email= :receiverEmail");
			receiverQuery.setParameter("receiverEmail", receiverEmail);
			GenericUser receiver = (GenericUser)receiverQuery.getSingleResult();
			
			Message message = new Message(messageId, sender, receiver, body);
			
			entityManager.persist(message);
	}

	private List<String> returnLineListMessages(List<Message> messages){
		
		List<String> messagesList = new ArrayList<String>();
		
		for(Message mess: messages){
			String sender = new String(mess.getSender().getFirstName() + "\t" + mess.getSender().getLastName()+ "\t" + mess.getSender().getEmail()+ "\t");
		    String receiver = new String(mess.getReceiver().getFirstName() + "\t" + mess.getReceiver().getLastName()+ "\t" + mess.getReceiver().getEmail()+ "\t");
		    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		    String date = new String(sdf.format(mess.getDate().getTime()) + "\t");
		    String body = new String(mess.getBody());

			String lineMessage = new String(sender + receiver + date + body);
			messagesList.add(lineMessage);			
		}
		return messagesList;
	}


}
