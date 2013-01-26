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

	public Double getFeedbackAverage() {
		
		Double zeroFeedback = new Double(this.zeroFeedback);
	    Double oneFeedback = new Double(this.oneFeedback);
		Double twoFeedback = new Double(this.twoFeedback);
		Double threeFeedback = new Double(this.threeFeedback);
		Double fourFeedback = new Double(this.fourFeedback);
		
		Double totalnumberOfFeedbacks = new Double(zeroFeedback + oneFeedback + twoFeedback + threeFeedback + fourFeedback);
		Double weightedSum = new Double(0 * zeroFeedback + 1 * oneFeedback + 2 * twoFeedback + 3 * threeFeedback + 4 * fourFeedback);
		
		if(totalnumberOfFeedbacks.equals(new Double(0))){
			return new Double(0);
		}
		
		return new Double(weightedSum/totalnumberOfFeedbacks);
	}
	
}
