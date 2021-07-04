package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Education;

public interface EducationService {
	DataResult<List<Education>> getAll();
	DataResult<List<Education>> getAllByResumeIdOrderByEndYearDesc(int resumeId);
	DataResult<List<Education>> getAllByResumeId(int resumeId);
	Result add(Education education);
	Result delete(Education education);
}
