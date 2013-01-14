package it.polimi.SWIMv2.SessionBeans;

import java.util.List;

import it.polimi.SWIMv2.EntityBeans.GenericUser;
import it.polimi.SWIMv2.Exceptions.VoidSearchException;

import javax.ejb.Local;

@Local
public interface SearchBeanLocal {
	
	public abstract List<GenericUser> searchForUsers(String f, String l, String c, String a) throws VoidSearchException;

}
