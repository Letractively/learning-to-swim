package it.polimi.SWIMv2.EntityBeans;

import javax.persistence.*;

public class Admin extends GenericUser{

	private static final long serialVersionUID = 1L;

	@Column(name = "Admin")
	private boolean admin = true;
}