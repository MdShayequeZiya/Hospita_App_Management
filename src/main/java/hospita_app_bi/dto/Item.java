package hospita_app_bi.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	
	private String itemType;
	private double price;
	
	private String expiryDate;
	private String itemName;
	
	
	// ITEM <-> MEDORDER 
	
	@ManyToMany(mappedBy = "items", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	private List<MedOrder> medOrders;

}
