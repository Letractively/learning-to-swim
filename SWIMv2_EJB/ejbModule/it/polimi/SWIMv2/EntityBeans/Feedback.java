package it.polimi.SWIMv2.EntityBeans;

import java.io.Serializable;

import javax.persistence.*;

@Embeddable
public class Feedback implements Serializable{
	
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

	public void setZeroFeedback(int zeroFeedback) {
		this.zeroFeedback = zeroFeedback;
	}

	public int getOneFeedback() {
		return oneFeedback;
	}

	public void setOneFeedback(int oneFeedback) {
		this.oneFeedback = oneFeedback;
	}

	public int getTwoFeedback() {
		return twoFeedback;
	}

	public void setTwoFeedback(int twoFeedback) {
		this.twoFeedback = twoFeedback;
	}

	public int getThreeFeedback() {
		return threeFeedback;
	}

	public void setThreeFeedback(int threeFeedback) {
		this.threeFeedback = threeFeedback;
	}

	public int getFourFeedback() {
		return fourFeedback;
	}

	public void setFourFeedback(int fourFeedback) {
		this.fourFeedback = fourFeedback;
	}

	
}
