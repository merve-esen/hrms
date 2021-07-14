package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ProgrammingTechnologySkill;

public interface ProgrammingTechnologySkillDao extends JpaRepository<ProgrammingTechnologySkill, Integer>{
	List<ProgrammingTechnologySkill> getAllByResumeId(int resumeId);
}
