package hospita_app_bi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.Person;

public class PersondDao {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	private EntityManager manager = factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();
	
	
	public Person findPerson(int personId) {
		return manager.find(Person.class, personId);
	}
	
	public Person savePerson(Person person) {
		transaction.begin();
		
		manager.persist(person);
		
		transaction.commit();
		
		return person;
		
	}

}
