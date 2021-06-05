package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ProgrammingTechnologySkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ProgrammingTechnologySkillDao;
import kodlamaio.hrms.entities.concretes.ProgrammingTechnologySkill;

@Service
public class ProgrammingTechnologySkillManager implements ProgrammingTechnologySkillService{
	
	private ProgrammingTechnologySkillDao programmingTechnologySkillDao;

	@Autowired
	public ProgrammingTechnologySkillManager(ProgrammingTechnologySkillDao programmingTechnologySkillDao) {
		super();
		this.programmingTechnologySkillDao = programmingTechnologySkillDao;
	}

	@Override
	public DataResult<List<ProgrammingTechnologySkill>> getAll() {
		return new SuccessDataResult<List<ProgrammingTechnologySkill>>(this.programmingTechnologySkillDao.findAll());
	}

	@Override
	public Result add(ProgrammingTechnologySkill programmingTechnologySkill) {
		this.programmingTechnologySkillDao.save(programmingTechnologySkill);
		return new SuccessResult();
	}

}
