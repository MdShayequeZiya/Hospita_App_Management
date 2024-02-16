package hospita_app_bi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.Encounter;

public class EncounterDao {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	private EntityManager manager = factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();

	public Encounter findEncounter(int encounterId) {
		return manager.find(Encounter.class, encounterId);
	}
	
	public boolean deleteEncounter(Encounter encounter) {
		
		transaction.begin();
		manager.remove(encounter);
		transaction.commit();
		
		return true;
	}
	
	public Encounter updateEncounter(Encounter encounter) {
		
		transaction.begin();
		
		manager.merge(encounter);
		
		transaction.commit();
		
		return encounter;
	}

}
