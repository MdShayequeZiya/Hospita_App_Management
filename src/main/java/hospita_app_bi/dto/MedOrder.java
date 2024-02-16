package hospita_app_bi.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MedOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int medOrderId;
	
	@CreationTimestamp
	private LocalDateTime orderDateTime;
	
	private int quantity;
	
	private String invoiceNumber;
	
	private String paymentMode;
	
	// MEDORDER <-> ENCOUNTER RELATION
	@ManyToOne
	@JoinColumn(name = "encounter_id")
	private Encounter encounter;
	
	
	// MEDORDER <-> ITEM RELATION
	@ManyToMany(cascade = {CascadeType.PERSIST})
	@JoinTable(joinColumns = @JoinColumn(name ="medorder_id"),
	inverseJoinColumns = @JoinColumn(name ="item_id"))
	private List<Item> items;

}
