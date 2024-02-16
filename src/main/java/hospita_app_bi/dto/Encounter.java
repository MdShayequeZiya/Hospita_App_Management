package hospita_app_bi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Encounter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int encounterId;
	
	private String symptom;
	private String visitedDoctor;
	
	@CreationTimestamp
	private LocalDateTime encounterDateAndTime;
	
	
	// ENCOUNTER <-> BRANCH RELATION
	@ManyToOne
	@JoinColumn(name = "branch_id")
	private Branch branch;
	
	
	// ENCOUNTER <-> PERSON RELATION
	@ManyToOne
	@JoinColumn(name = "person_id")
	private Person person;
	
	
	// ENCOUNTER <-> MEDORDER RELATION
	@OneToMany (mappedBy = "encounter", cascade = CascadeType.ALL)
	private List<MedOrder> medOrders;
	
	

}
