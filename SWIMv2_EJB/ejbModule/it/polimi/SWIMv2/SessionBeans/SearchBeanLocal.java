package it.polimi.SWIMv2.SessionBeans;

import java.util.List;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.VoidSearchException;

import javax.ejb.Local;

@Local
public interface SearchBeanLocal {
	
	public List<String> searchByName(String firstName, String lastName);
	
	public List<String> searchByAbility(Long ability);

}
