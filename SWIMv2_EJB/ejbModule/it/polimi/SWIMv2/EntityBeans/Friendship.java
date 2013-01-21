package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {

	
	public Friendship(){}

	public Friendship(GenericUser u1, GenericUser u2, boolean type){
		this.friendshipKey = new FriendshipKey(u1,u2);
		this.confirmed = false;
		this.direct = type;
	}
	
	
	
	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@ManyToOne
	FriendshipKey friendshipKey;

	@Column(name = "Confirmed")
	private boolean confirmed;

	@Column(name = "Type")
	private boolean direct;

	
	public FriendshipKey getFriendshipKey() {
		return friendshipKey;
	}

	public void setFriendshipKey(FriendshipKey friendshipKey) {
		this.friendshipKey = friendshipKey;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmation) {
		this.confirmed = confirmation;
	}

	public boolean isDirect() {
		return direct;
	}

	public void setType(boolean type) {
		this.direct = type;
	}
	
	

}