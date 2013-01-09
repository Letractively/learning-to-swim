package it.polimi.SWIMv2.Database;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="User")
public abstract class GenericUser implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;

	@Column(name = "First_Name", length = 32, nullable = false)
	private String firstName;
	
	@Column(name = "Last_Name", length = 32, nullable = false)
	private String lastName;
	
	@Column(name = "Email", length = 32, unique = true, nullable = false)
	private String email;
	
	@Column(name = "Password", length = 32, nullable = false)
	private String password;
	
	@Column(name = "City", length = 32)
	private String city;
	
	/*@Column(name = "Admin")
	private boolean admin;*/
	
	
	@Column(name = "Zero_Feedback", length = 10)
	private int zeroFeedback;
	
	@Column(name = "One_Feedback", length = 10)
	private int oneFeedback;
	
	@Column(name = "Two_Feedback", length = 10)
	private int twoFeedback;
	
	@Column(name = "Three_Feedback", length = 10)
	private int threeFeedback;
	
	@Column(name = "Four_Feedback", length = 10)
	private int fourFeedback;
	
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/*public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}*/

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

	public Long getId() {
		return id;
	}

	//prova


}
