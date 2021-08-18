package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateDao extends JpaRepository<EmployerUpdate, Integer>{
	EmployerUpdate getById(int id);
	List<EmployerUpdate> getByDeletedFalseAndConfirmedFalse();
	List<EmployerUpdate> getByEmployer_Id(int employerId);
	List<EmployerUpdate> getByEmployer_IdAndDeletedFalse(int employerId);
}
