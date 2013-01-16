package it.polimi.SWIMv2.SessionBeans;

import javax.ejb.Local;

@Local
public interface MessageBeanLocal {
	
	public abstract boolean validReceiver(String receiverEmail);

	public abstract void insertToDatabase(String senderEmail, String receiverEmail, String body);
	

}

