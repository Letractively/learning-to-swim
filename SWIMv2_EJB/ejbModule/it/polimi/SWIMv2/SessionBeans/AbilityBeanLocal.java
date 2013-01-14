package it.polimi.SWIMv2.SessionBeans;

import it.polimi.SWIMv2.EntityBeans.Ability;
import it.polimi.SWIMv2.EntityBeans.GenericUser;

import javax.ejb.Local;

@Local
public interface AbilityBeanLocal {
	
	public abstract void addAbilityToUser(GenericUser u, Ability a);

}
