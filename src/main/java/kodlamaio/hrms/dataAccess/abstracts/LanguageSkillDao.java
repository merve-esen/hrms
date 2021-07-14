package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LanguageSkill;

public interface LanguageSkillDao extends JpaRepository<LanguageSkill, Integer>{
	List<LanguageSkill> getAllByResumeId(int resumeId);
}
