package it.polimi.SWIMv2.SessionBeans;

import javax.ejb.Local;

@Local
public interface FeedbackBeanLocal {
	
	public abstract boolean giveFeedback(String userEmail,Integer value);
	
	public abstract Double getFeedbackAverage(String userEmail);

}
