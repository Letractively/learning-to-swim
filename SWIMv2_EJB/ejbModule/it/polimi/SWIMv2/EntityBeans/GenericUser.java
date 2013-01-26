package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "User")
public abstract class GenericUser implements Serializable {
	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "First_Name", length = 32, nullable = false)
	private String firstName;

	@Column(name = "Last_Name", length = 32, nullable = false)
	private String lastName;

	@Column(name = "Email", length = 32, unique = true, nullable = false)
	private String email;

	@Column(name = "Password", length = 500, nullable = false)
	private String password;

	@Column(name = "City", length = 32)
	private String city;

	@Embedded
	private Feedback feedback;
	
	@Column(name = "Registration_Code", length = 30)
	private String registrationCode;
	
	@Column(name = "Confirmed")
	private boolean confirmed;
	
	public GenericUser(){}
	
	public GenericUser(String firstName, String lastName, String email, String password, String city){
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.city = city;
		this.feedback = new Feedback();
	
	}
	
	
	
	public boolean isConfirmed(){
		return this.confirmed;
	}
	
	public void setConfirmed(boolean confirmed){
		this.confirmed = confirmed;
	}
	
	public String getRegistrationCode(){
		return this.registrationCode;
	}
	
	public void setRegistrationCode(String registrationCode){
		this.registrationCode = registrationCode;
	}
	
	public Feedback getFeedback() {
		return feedback;
	}

	public void setFeedback(Feedback feedback) {
		this.feedback = feedback;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getId() {
		return id;
	}

	public Set<GenericUser> getFriends() {
		return friends;
	}

	@OneToMany(
			targetEntity=it.polimi.SWIMv2.EntityBeans.Friendship.class
            )
	private Set<GenericUser> friends;
	
	
	
	public Double getFeedbackAverage() {
		return this.getFeedback().getFeedbackAverage();
	}
}
