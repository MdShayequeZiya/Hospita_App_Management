package hospita_app_bi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Branch {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int branchId;
	
	private String branchName;
	private String branchHead;
	private String isICUAvailable;
	
	// HOSPITAL RELATION
	@ManyToOne
	@JoinColumn(name = "hospital_id")
	private Hospital hospital;
	
	
	//ADDRESS RELATION
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;
	
	
	// BRANCH <-> ENCOUNTER REALTION
	@OneToMany(mappedBy = "branch", cascade = CascadeType.ALL)
	private List<Encounter> encounters;

}
