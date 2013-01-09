package it.polimi.SWIMv2.Database;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="Ability")
public class Ability implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	private String name;
	

}
