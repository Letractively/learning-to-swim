package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "Friendship")
public class Friendship implements Serializable {


	@PersistenceContext(unitName = "SWIMv2_PU")
	
	private static final long serialVersionUID = 1L;

	/*@EmbeddedId
	@ManyToOne
	FriendshipKey friendshipKey;*/

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long id;
	
	//@Column(name = "Friend1")
	@ManyToOne
    private GenericUser friend1;

	//@Column(name = "Friend2")
	@ManyToOne
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

	public Friendship(GenericUser u1, GenericUser u2){
		this.friend1 = u1;
		this.friend2 = u2;
	}
	
	public Friendship(){}
	
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
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GenericUser getFriend1() {
		return friend1;
	}

	public void setFriend1(GenericUser friend1) {
		this.friend1 = friend1;
	}

	public GenericUser getFriend2() {
		return friend2;
	}

	public void setFriend2(GenericUser friend2) {
		this.friend2 = friend2;
	}


}