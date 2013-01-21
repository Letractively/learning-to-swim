package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {

	
	public Friendship(){}

	public Friendship(GenericUser u1, GenericUser u2, boolean type){
		this.friendshipKey = new FriendshipKey(u1,u2);
		this.confirmation = false;
		this.direct = type;
	}
	
	
	
	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	@Id
    FriendshipKey friendshipKey;

	@Column(name = "Confirmed")
	private boolean confirmation;

	@Column(name = "Direct")
	private boolean direct;

	
	public FriendshipKey getFriendshipKey() {
		return friendshipKey;
	}

	public void setFriendshipKey(FriendshipKey friendshipKey) {
		this.friendshipKey = friendshipKey;
	}

	public boolean isConfirmation() {
		return confirmation;
	}

	public void setConfirmation(boolean confirmation) {
		this.confirmation = confirmation;
	}

	public boolean isType() {
		return direct;
	}

	public void setType(boolean type) {
		this.direct = type;
	}
	
	

}