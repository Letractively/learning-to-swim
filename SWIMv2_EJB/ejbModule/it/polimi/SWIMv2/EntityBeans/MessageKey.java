package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class MessageKey implements Serializable{
	
	
	public MessageKey(){}
	
	public MessageKey(Long id,GenericUser senderUser){
		this.id = id;
		this.sender = senderUser;
	
	}
	
	
	private static final long serialVersionUID = 1L;

	    @Column(name = "ID")
	    private Long id;
	
		@ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class)
		private GenericUser sender ;

		public GenericUser getSender() {
			return sender;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			result = prime * result
					+ ((sender == null) ? 0 : sender.hashCode());
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
			MessageKey other = (MessageKey) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			if (sender == null) {
				if (other.sender != null)
					return false;
			} else if (!sender.equals(other.sender))
				return false;
			return true;
		}

		
		
}
