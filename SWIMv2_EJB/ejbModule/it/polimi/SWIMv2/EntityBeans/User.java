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
	
	
	public User(){}
	
	public User(String firstName, String lastName, String email, String password, String city, boolean admin){
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setEmail(email);
		super.setPassword(password);
		super.setCity(city);
		this.admin = admin;
		super.setFeedback(new Feedback());
		
	}
}
