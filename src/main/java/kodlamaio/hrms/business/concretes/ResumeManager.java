package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Resume;

@Service
public class ResumeManager implements ResumeService{

	private ResumeDao resumeDao;

	@Autowired
	public ResumeManager(ResumeDao resumeDao) {
		super();
		this.resumeDao = resumeDao;
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll());
	}

	@Override
	public Result add(Resume resume) {
		this.resumeDao.save(resume);
		return new SuccessResult();
	}

	@Override
	public Result update(Resume resume) {
		this.resumeDao.save(resume);
		return new SuccessResult();
	}

	/*@Override
	public DataResult<Resume> getById(int id) {
		return new SuccessDataResult<Resume>(this.resumeDao.getById(id));
	}*/

	@Override
	public DataResult<Resume> getByCandidate_Id(int candidateId) {
		return new SuccessDataResult<Resume>(this.resumeDao.getByCandidate_Id(candidateId));
	}

	@Override
    public Result updateGithubLink(int resumeId, String githubLink) {

        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("Böyle bir önzgeçmiş yok");
        }else if(!githubLink.startsWith("https://github.com")){
            if(!githubLink.startsWith("github.com")){
                return new ErrorResult("Geçerli bir github linki değil");
            }
        }

        Resume resume=this.resumeDao.getById(resumeId);
        resume.setGithubLink(githubLink);
        this.resumeDao.save(resume);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteGithubLink(int resumeId) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("Böyle bir cv yok");
        }
        Resume resume=this.resumeDao.getById(resumeId);
        resume.setGithubLink(null);
        this.resumeDao.save(resume);

        return new SuccessResult("Github adresi kaldırıldı");
    }

    @Override
    public Result updateLinkedinLink(int resumeId, String linkedinLink) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("Böyle bir özgeçmiş yok");
        } else if(!linkedinLink.startsWith("https://www.linkedin.com") &&
                !linkedinLink.startsWith("www.linkedin.com") &&
                !linkedinLink.startsWith("https://linkedin.com") &&
                !linkedinLink.startsWith("linkedin.com")){
            return new ErrorResult("Geçerli bir linked in adresi değil");
        }
        Resume resume=this.resumeDao.getById(resumeId);
        resume.setLinkedinLink(linkedinLink);
        this.resumeDao.save(resume);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteLinkedinLink(int resumeId) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("Böyle bir özgeçmiş yok");
        }
        Resume resume=this.resumeDao.getById(resumeId);
        resume.setLinkedinLink(null);
        this.resumeDao.save(resume);
        return new SuccessResult("Linkedin adresi silindi");
    }

    @Override
    public Result updateObjective(int resumeId, String objective) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("Böyle bir özgeçmiş yok");
        } else if(objective.length()<=2){
            return new ErrorResult("Ön yazı 2 krakterden uzun olmalıdır");
        }
        Resume resume=this.resumeDao.getById(resumeId);
        resume.setObjective(objective);
        this.resumeDao.save(resume);
        return new SuccessResult("Ön yazı kaydedildi");
    }

}
