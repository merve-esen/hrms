package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.WorkExperienceDao;
import kodlamaio.hrms.entities.concretes.WorkExperience;

@Service
public class WorkExperienceManager implements WorkExperienceService{
	
	private WorkExperienceDao workExperienceDao;

	@Autowired
	public WorkExperienceManager(WorkExperienceDao workExperienceDao) {
		super();
		this.workExperienceDao = workExperienceDao;
	}

	@Override
	public DataResult<List<WorkExperience>> getAll() {
		return new SuccessDataResult<List<WorkExperience>>(this.workExperienceDao.findAll());
	}

	@Override
	public Result add(WorkExperience workExperience) {
		this.workExperienceDao.save(workExperience);
		return new SuccessResult();
	}
	
	@Override
	public DataResult<List<WorkExperience>> getAllByResumeIdOrderByEndYearDesc(int resumeId) {
		return new SuccessDataResult<List<WorkExperience>>(this.workExperienceDao.getAllByResumeIdOrderByEndYearDesc(resumeId));
	}

	@Override
	public DataResult<List<WorkExperience>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<WorkExperience>>(this.workExperienceDao.getAllByResumeId(resumeId));
	}

}
