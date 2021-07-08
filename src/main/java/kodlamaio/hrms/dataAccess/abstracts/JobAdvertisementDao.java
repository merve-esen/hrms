package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	JobAdvertisement getById(int id);
	List<JobAdvertisement> getByIsActiveTrue();
	List<JobAdvertisement> getByIsActiveTrueOrderByApplicationDeadline();
	List<JobAdvertisement> getByIsActiveTrueOrderByApplicationDeadlineDesc();
	List<JobAdvertisement> getByEmployer_Id(int employerId);
	List<JobAdvertisement> getByEmployer_IdAndIsActiveTrue(int employerId);
	List<JobAdvertisement> getByConfirmedTrue();
	List<JobAdvertisement> getByEmployer_IdAndConfirmedTrue(int employerId);
}
