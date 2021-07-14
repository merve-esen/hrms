package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.LanguageSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.LanguageSkillDao;
import kodlamaio.hrms.entities.concretes.LanguageSkill;

@Service
public class LanguageSkillManager implements LanguageSkillService{

	private LanguageSkillDao languageSkillDao;

	@Autowired
	public LanguageSkillManager(LanguageSkillDao languageSkillDao) {
		super();
		this.languageSkillDao = languageSkillDao;
	}

	@Override
	public DataResult<List<LanguageSkill>> getAll() {
		return new SuccessDataResult<List<LanguageSkill>>(this.languageSkillDao.findAll());
	}

	@Override
	public DataResult<List<LanguageSkill>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<LanguageSkill>>(this.languageSkillDao.getAllByResumeId(resumeId));
	}

	@Override
	public Result add(LanguageSkill languageSkill) {
		this.languageSkillDao.save(languageSkill);
		return new SuccessResult();
	}

	@Override
	public Result delete(int languageSkillId) {
		if(!this.languageSkillDao.existsById(languageSkillId)){
            return new ErrorResult("Silinecek kayıt bulunamadı");
        }
        this.languageSkillDao.deleteById(languageSkillId);
        return new SuccessResult("Silindi");
	}

}
