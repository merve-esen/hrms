package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;

public interface ResumeService {
	DataResult<List<Resume>> getAll();
	Result add(Resume resume);
	Result update(Resume resume);
	//DataResult<Resume> getById(int id);
    DataResult<Resume> getByCandidate_Id(int candidateId);
    Result updateGithubLink(int resumeId, String githubLink);
    Result deleteGithubLink(int resumeId);
    Result updateLinkedinLink(int resumeId, String linkedinLink);
    Result deleteLinkedinLink(int resumeId);
    Result updateObjective(int resumeId, String objective);
}
