package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

public interface JobAdvertisementDao extends JpaRepository<JobAdvertisement, Integer>{
	JobAdvertisement getById(int id);
	List<JobAdvertisement> getByIsActiveTrue();
	List<JobAdvertisement> getByIsActiveTrueOrderByApplicationDeadline();
	List<JobAdvertisement> getByIsActiveTrueOrderByApplicationDeadlineDesc();
	List<JobAdvertisement> getByEmployer_Id(int employerId);
	List<JobAdvertisement> getByEmployer_IdAndIsActiveTrue(int employerId);
	List<JobAdvertisement> getByConfirmedIsNull();
	List<JobAdvertisement> getByConfirmedTrue();
	List<JobAdvertisement> getByEmployer_IdAndConfirmedTrue(int employerId);

	@Query("Select j from kodlamaio.hrms.entities.concretes.JobAdvertisement j where ((:#{#filter.cityId}) IS NULL OR j.city.id IN (:#{#filter.cityId}))"
	        +" and ((:#{#filter.jobPositionId}) IS NULL OR j.jobPosition.id IN (:#{#filter.jobPositionId}))"
	        +" and ((:#{#filter.workplaceId}) IS NULL OR j.workplace.id IN (:#{#filter.workplaceId}))"
	        +" and ((:#{#filter.workTimeId}) IS NULL OR j.workTime.id IN (:#{#filter.workTimeId}))"
	        +" and j.isActive=true and j.confirmed=true")
    Page<JobAdvertisement> getByFilter(@Param("filter") JobAdvertisementFilter jobAdvertisementFilter, Pageable pageable);
}
