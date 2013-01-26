package it.polimi.SWIMv2.SessionBeans;

import java.util.List;

import javax.ejb.Local;

@Local
public interface MessageBeanLocal {
	
	public abstract boolean validReceiver(String receiverEmail);

	public abstract void insertToDatabase(String senderEmail, String receiverEmail, String body);
	
	public abstract List<String> returnReceivedMessages(String userEmail);

	public abstract List<String> returnSendedMessages(String userEmail);
	
}

