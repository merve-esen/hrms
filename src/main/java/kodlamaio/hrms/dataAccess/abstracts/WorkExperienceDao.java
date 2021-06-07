package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.WorkExperience;

public interface WorkExperienceDao extends JpaRepository<WorkExperience, Integer>{
	List<WorkExperience> getAllByResumeIdOrderByEndYearDesc(int resumeId);
	List<WorkExperience> getAllByResumeId(int resumeId);
}
