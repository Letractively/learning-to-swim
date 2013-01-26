package it.polimi.SWIMv2.SessionBeans;


import java.util.List;
import javax.ejb.Local;

@Local
public interface AbilityBeanLocal {
	
	public abstract boolean validAbility(String abilityName);
	
	public abstract void addAbilityToUser(String userEmail, Long abilityId);

	public abstract void createAbility(String name, String description, String creatorEmail);
	
	public abstract List<String> returnAbilityToAdd(String userEmail);

	public abstract List<String> returnUserAbilities(String userEmail);

	public abstract List<String> returnTotalAbilityList();

}
