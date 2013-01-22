package it.polimi.SWIMv2.SessionBeans;

import java.util.List;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.VoidSearchException;

import javax.ejb.Local;

@Local
public interface SearchBeanLocal {
	
	public List<GenericUser> searchByName(String firstName, String lastName);
	
	public List<GenericUser> searchByAbility(String ability);

}
