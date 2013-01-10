package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MessageKey implements Serializable{
	
	public MessageKey(){}
	
	public MessageKey(Date date,Long sender){
		
		this.date= date;
		this.sender=sender;
	
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		return true;
	}


	private static final long serialVersionUID = 1L;

		@Column(name="Date")
		private Date date;
		
		@Column(name="Sender")
		private Long sender ;

		public Long getSender() {
			return sender;
		}

		public Date getDate() {
			return date;
		}

		
}
