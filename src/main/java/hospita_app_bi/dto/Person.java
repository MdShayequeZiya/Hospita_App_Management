package hospita_app_bi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int personId;
	
	private String personName;
	private int personAge;
	private String bloodGroup;
	
	private double weight;
	
	
	// PERSON <-> ENCOUNTER RELATION
	@OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
	private List<Encounter> encounters;

}
