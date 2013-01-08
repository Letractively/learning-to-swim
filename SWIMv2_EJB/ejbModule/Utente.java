import javax.persistence.*;


@Entity
@Table(name="UTENTI")
public class Utente {
	@Id
	@GeneratedValue
	private Long ID;

	@Column(length = 32, nullable = false)
	private String Nome;
	
	@Column(length = 32, nullable = false)
	private String Cognome;
	
	@Column(length = 32, unique = true, nullable = false)
	private String Mail;
	
	@Column(length = 32, nullable = false)
	private String Password;
	
	
	public Long getID() {
		return ID;
	}

	public void setID(Long iD) {
		ID = iD;
	}

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getCognome() {
		return Cognome;
	}

	public void setCognome(String cognome) {
		Cognome = cognome;
	}

	public String getMail() {
		return Mail;
	}

	public void setMail(String mail) {
		Mail = mail;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	

}
