package hospita_app_bi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Hospital {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int hospitalId;
	
	private String hospitalName;
	private String founderName;
	private String yearFound;
	
	
	@UpdateTimestamp
	private LocalDateTime localDateTime;
	
	
	// HOSPITAL <-> BRANCH RELATION
	@OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL)
	private List<Branch> branches;
	

}
