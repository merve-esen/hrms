package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageSkill;

public interface LanguageSkillService {
	DataResult<List<LanguageSkill>> getAll();
	Result add(LanguageSkill languageSkill);
	Result delete(LanguageSkill languageSkill);
}
