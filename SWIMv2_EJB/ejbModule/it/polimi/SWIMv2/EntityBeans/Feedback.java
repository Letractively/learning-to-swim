package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Feedback implements Serializable{
	
	public Feedback(){
		
		this.zeroFeedback=0;
		this.oneFeedback=0;
		this.twoFeedback=0;
		this.threeFeedback=0;
		this.fourFeedback=0;
	
	}
	
	private static final long serialVersionUID = 1L;

	@Column(name="Feedback0")
	private int zeroFeedback;
	
	@Column(name="Feedback1")
	private int oneFeedback;

	@Column(name="Feedback2")
    private int twoFeedback;

	@Column(name="Feedback3")
    private int threeFeedback;

	@Column(name="Feedback4")
	private int fourFeedback;

	

	public int getZeroFeedback() {
		return zeroFeedback;
	}

	public void addZeroFeedback() {
		this.zeroFeedback++;
	}

	public int getOneFeedback() {
		return oneFeedback;
	}

	public void addOneFeedback() {
		this.oneFeedback++;
	}

	public int getTwoFeedback() {
		return twoFeedback;
	}

	public void addTwoFeedback() {
		this.twoFeedback++;
	}

	public int getThreeFeedback() {
		return threeFeedback;
	}

	public void addThreeFeedback() {
		this.threeFeedback++;
	}

	public int getFourFeedback() {
		return fourFeedback;
	}

	public void addFourFeedback() {
		this.fourFeedback++;
	}

	
}
