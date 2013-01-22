package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class UserAbilitiesKey implements Serializable{
	
	public UserAbilitiesKey(GenericUser user, Ability ability){
		
		this.user = user;
		this.ability = ability;
		
	}

	public UserAbilitiesKey(){}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ability == null) ? 0 : ability.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserAbilitiesKey other = (UserAbilitiesKey) obj;
		if (ability == null) {
			if (other.ability != null)
				return false;
		} else if (!ability.equals(other.ability))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class)
    private GenericUser user;
    

	@ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.Ability.class)
	private Ability ability;
	
	
	public GenericUser getUser() {
		return user;
	}

	public void setUser(GenericUser user) {
		this.user = user;
	}

	public Ability getAbility() {
		return ability;
	}

	public void setAbility(Ability ability) {
		this.ability = ability;
	}

}
