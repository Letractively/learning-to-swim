package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserAbilities
 *
 */
@Entity
@Table(name="User_Abilities")
public class UserAbilities implements Serializable {

	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	public UserAbilities() {
		super();
	}
	
	@Id
	private UserAbilitiesKey userAbilitiesKey;
	
	

	public UserAbilitiesKey getUserAbilitiesKey() {
		return userAbilitiesKey;
	}

	public void setUserAbilitiesKey(UserAbilitiesKey userAbilitiesKey) {
		this.userAbilitiesKey = userAbilitiesKey;
	}
   
}
