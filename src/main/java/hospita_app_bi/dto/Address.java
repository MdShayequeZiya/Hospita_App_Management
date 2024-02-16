package hospita_app_bi.dto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	
	private String block;
	private String city;
	private String state;
	
	private String pincode;
	
	@CreationTimestamp
	private LocalDateTime localDateTime;
	
	// BRANCH <-> ADDRESS RELATION
	@OneToOne(mappedBy = "address")
	private Branch branch;

	@Override
	public String toString() {
		return "BLOCK= " + block + ", CITY= " + city + ", STATE= " + state + ", PINCODE= " + pincode;
	}
	
	

}
