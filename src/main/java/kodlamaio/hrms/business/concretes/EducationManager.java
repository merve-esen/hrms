package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EducationService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EducationDao;
import kodlamaio.hrms.entities.concretes.Education;

@Service
public class EducationManager implements EducationService{

	private EducationDao educationDao;

	@Autowired
	public EducationManager(EducationDao educationDao) {
		super();
		this.educationDao = educationDao;
	}

	@Override
	public DataResult<List<Education>> getAll() {
		return new SuccessDataResult<List<Education>>(this.educationDao.findAll());
	}

	@Override
	public DataResult<List<Education>> getAllByResumeIdOrderByEndYearDesc(int resumeId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getAllByResumeIdOrderByEndYearDesc(resumeId));
	}

	@Override
	public DataResult<List<Education>> getAllByResumeId(int resumeId) {
		return new SuccessDataResult<List<Education>>(this.educationDao.getAllByResumeId(resumeId));
	}

	@Override
	public Result add(Education education) {
		this.educationDao.save(education);
		return new SuccessResult();
	}

	@Override
	public Result delete(int educationId) {
		if(!this.educationDao.existsById(educationId)){
            return new ErrorResult("Silinecek kayıt bulunamadı");
        }
        this.educationDao.deleteById(educationId);
        return new SuccessResult("Silindi");
	}

}
