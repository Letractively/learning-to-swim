package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import java.util.HashSet;
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

	
	/*@ManyToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class
            )
	@JoinTable(
			name = "Friendship",
			joinColumns = @JoinColumn(name = "id1"),
			inverseJoinColumns = @JoinColumn(name = "id2")
			)
	private Set<GenericUser> friends = new HashSet<GenericUser>();
	*/
	@ManyToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Ability.class
            )
	@JoinTable(
			name = "User_Abilities",
			joinColumns = @JoinColumn(name = "User"),
			inverseJoinColumns = @JoinColumn(name = "Ability")
			)
	private Set<Ability> abilities = new HashSet<Ability>();
	
	public Set<Ability> getAbilities() {
		return abilities;
	}

	public void setAbilities(Set<Ability> abilities) {
		this.abilities = abilities;
	}
	
	public void addAbility(Ability a){
		this.abilities.add(a);
	}


	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Message.class
            )
	private Set<Message> senderMessages = new HashSet<Message>();

	
	@OneToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Message.class
            )
	private Set<Message> receiveMessages = new HashSet<Message>();
	/*@ManyToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.Message.class
            )
	@JoinTable(
			name = "Received_Messages",
			joinColumns = @JoinColumn(name = "Receiver"),
			inverseJoinColumns = @JoinColumn(name = "Message")
			)*/

	
}
