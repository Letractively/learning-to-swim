package it.polimi.SWIMv2.EntityBeans;

import javax.persistence.*;

/**
 * La entity corrispondente alla tabella User
 * @author emanuele
 *
 */
@Entity
public class User extends GenericUser{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "Admin")
	private boolean admin = false;
}
