package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;


@Entity
@Table(name = "Message")
public class Message implements Serializable{

	public Message(Long idMessage, GenericUser sender, GenericUser receiver, String body){
		
		this.messageKey = new MessageKey(idMessage,sender);
		
		this.date = Calendar.getInstance();
		this.body = body;
		this.receiver = receiver;
		
		
	}
	
	public Message(){}
	
	private static final long serialVersionUID = 1L;
	
	@Id private MessageKey messageKey;
	
	@Column(name="Date")
	private Calendar date;
	
	@Column(name = "Body", length= 3000)
	private String body;

	@ManyToOne(targetEntity=it.polimi.SWIMv2.EntityBeans.GenericUser.class)
	private GenericUser receiver;
	


	public GenericUser getReceiver() {
		return receiver;
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
	
	public Calendar getDate() {
		return date;
	}
	
	public GenericUser getSender(){
		return this.getMessageKey().getSender();
	}
	
	
}
