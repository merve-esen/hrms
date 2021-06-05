package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.LanguageSkill;

public interface LanguageSkillDao extends JpaRepository<LanguageSkill, Integer>{

}
