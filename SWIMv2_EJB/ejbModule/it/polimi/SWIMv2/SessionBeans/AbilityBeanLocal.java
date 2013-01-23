package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Ability;

import java.util.*;

import javax.ejb.Local;

@Local
public interface AbilityBeanLocal {
	
	public abstract void addAbilityToUser(String userEmail, int ability);

	public abstract void createAbility(String name, String description, String creatorEmail);

	public abstract Map<Ability,Boolean> getAbilitiesByUser(String email);

}
