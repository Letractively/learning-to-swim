package it.polimi.SWIMv2.EntityBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Admin extends GenericUser{

	private static final long serialVersionUID = 1L;

	@Column(name = "Admin")
	private boolean admin = true;
	
	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Ability.class
            )
	private Set<Ability> abilities = new HashSet<Ability>();

}
