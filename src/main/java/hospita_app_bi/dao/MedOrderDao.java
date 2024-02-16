package hospita_app_bi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.MedOrder;

public class MedOrderDao {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	public MedOrder saveMedOrder(MedOrder medOrder) {
		
		transaction.begin();

		manager.persist(medOrder);
		transaction.commit();
		
		return medOrder;
	}
	
	public boolean deleteMedOder(MedOrder medOrder) {
		
		transaction.begin();
		manager.remove(medOrder);
		transaction.commit();
		
		return true;
	}
	
	public MedOrder findMedOrder(int medOrderId) {
		return manager.find(MedOrder.class, medOrderId);
	}

}
