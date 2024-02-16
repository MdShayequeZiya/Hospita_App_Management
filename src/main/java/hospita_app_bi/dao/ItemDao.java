package hospita_app_bi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.Item;



public class ItemDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public Item saveItem (Item item) {
		
		transaction.begin();
		
		manager.persist(item);
		
		transaction.commit();
		
		return item;
		
	}
	
	public Item findItem(int itemId) {
		
		return manager.find(Item.class, itemId);
		
	}
	
	

}
