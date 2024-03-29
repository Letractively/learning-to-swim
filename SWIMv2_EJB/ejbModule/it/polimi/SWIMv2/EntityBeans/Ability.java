package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Ability")
public class Ability implements Serializable{
	
	public Ability(String name, String description, Admin creator){
		
		this.name = name;
		this.description = description;
		this.creator = creator;
	}

    public Ability(){}
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@Column(name= "Name", length=32, nullable = false, unique= true)
	private String name;
	
	@Column(name= "Description", length= 500)
	private String description;
	
	@ManyToOne
	@JoinColumn(name="Creator")
	private Admin creator;

	
	
	public Admin getCreator() {
		return creator;
	}

	public void setCreator(Admin creator) {
		this.creator = creator;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}
	
	
	
	/*@ManyToMany(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class
            )
	@JoinTable(
			name = "User_Abilities",
			joinColumns = @JoinColumn(name = "Ability"),
			inverseJoinColumns = @JoinColumn(name = "User")
			)
	private Set<GenericUser> users = new HashSet<GenericUser>();*/
	
	/*@OneToMany(
			targetEntity=it.polimi.SWIMv2.EntityBeans.UserAbilities.class
            )
	private Set<GenericUser> users;*/
	


}
