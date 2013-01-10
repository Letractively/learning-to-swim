package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "Message")
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId private MessageKey messageKey;
	
	@Column(name = "Body", length= 3000)
	private String body;

	@Column(name = "Receiver", nullable = false)
	private Long receiver;
	
	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public MessageKey getMessageKey() {
		return messageKey;
	}
	
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class
            )
	private GenericUser sender;
	
	@ManyToOne(
			cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class
            )
	private GenericUser receiverID;
	
}
