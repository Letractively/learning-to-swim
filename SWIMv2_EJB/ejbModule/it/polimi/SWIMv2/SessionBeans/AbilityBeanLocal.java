package it.polimi.SWIMv2.SessionBeans;


import javax.ejb.Local;

@Local
public interface AbilityBeanLocal {
	
	public abstract void addAbilityToUser(String userEmail, int ability);

	public abstract void createAbility(String name, String description, String creatorEmail);

}
