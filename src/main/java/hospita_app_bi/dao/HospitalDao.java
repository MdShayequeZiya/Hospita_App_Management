package hospita_app_bi.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import hospita_app_bi.dto.Branch;
import hospita_app_bi.dto.Hospital;

public class HospitalDao {
	
	private EntityManagerFactory factory = Persistence.createEntityManagerFactory("hospital2");
	private EntityManager manager = factory.createEntityManager();
	private EntityTransaction transaction = manager.getTransaction();
	
	
	public Hospital saveHospital(Hospital hospital) {
		
		transaction.begin();
		manager.persist(hospital);
		transaction.commit();
		
		return hospital;
		
	}
	
	public Hospital updateBranchList(int hospitalId, Branch branch) {
		
		Hospital hospital = findHospital(hospitalId);

		List<Branch> branches = hospital.getBranches();

		if (branches == null) {
			branches = new ArrayList<Branch>(Arrays.asList(branch));
		} else if (!branches.contains(branch)) {
			branches.add(branch);
		} else {
			return null;
		}

		hospital.setBranches(branches);

		transaction.begin();

		manager.merge(hospital);

		transaction.commit();

		return hospital;
		
	}


	public boolean deleteHospital(int hospitalId) {
		
		Hospital hospitalToDelete = manager.find(Hospital.class, hospitalId);
		
		transaction.begin();
		manager.remove(hospitalToDelete);
		transaction.commit();
		
		return true;
		
	}
	
	public Hospital findHospital(int hospitalId) {
		return manager.find(Hospital.class, hospitalId);
	}

}
