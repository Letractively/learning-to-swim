package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {


	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId 
	private FriendshipKey friendshipKey;*/

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "Friend1")
    private GenericUser friend1;

	@Column(name = "Friend2")
    private GenericUser friend2 ;

	
	@Column(name = "Confirmed")
	private boolean confirmation;

	@Column(name = "Type")
	private boolean type;

	/*public FriendshipKey getFriendshipKey() {
		return friendshipKey;
	}

	public void setFriendshipKey(FriendshipKey friendshipKey) {
		this.friendshipKey = friendshipKey;
	}*/

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}


}