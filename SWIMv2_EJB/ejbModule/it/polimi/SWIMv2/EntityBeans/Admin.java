package it.polimi.SWIMv2.EntityBeans;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Admin extends GenericUser{

	private static final long serialVersionUID = 1L;

	@Column(name = "Admin")
	private boolean admin = true;
	
	public Admin(){}
	
	public Admin(String firstName, String lastName, String email, String password, String city, boolean admin){
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setEmail(email);
		super.setPassword(password);
		super.setCity(city);
		this.admin = admin;
		
	}
	
	//CON QUESTA RELAZIONE IL LOGIN NON FUNZIONA, ERGO VA RIPENSATA
	/*
	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Ability.class
            )
	private Set<Ability> abilities;
*/
}
