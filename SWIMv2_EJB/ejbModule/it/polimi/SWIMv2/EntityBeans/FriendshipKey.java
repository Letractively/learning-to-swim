package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;




@Embeddable
public class FriendshipKey implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	public FriendshipKey(){}
	
	public FriendshipKey(GenericUser User1, GenericUser User2){
	
		this.friend1 = User1;
		this.friend2 = User2;
	
	}
	


		@ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class)
	    private GenericUser friend1;
	    
	    @ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class)
		private GenericUser friend2;

	
	    public GenericUser getFriend1() {
			return friend1;
		}

		public GenericUser getFriend2() {
			return friend2;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((friend1 == null) ? 0 : friend1.hashCode());
			result = prime * result + ((friend2 == null) ? 0 : friend2.hashCode());
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
			FriendshipKey other = (FriendshipKey) obj;
			if (friend1 == null) {
				if (other.friend1 != null)
					return false;
			} else if (!friend1.equals(other.friend1))
				return false;
			if (friend2 == null) {
				if (other.friend2 != null)
					return false;
			} else if (!friend2.equals(other.friend2))
				return false;
			return true;
		}


		

}
