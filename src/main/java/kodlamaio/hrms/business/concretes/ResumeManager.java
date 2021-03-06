package kodlamaio.hrms.business.concretes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.services.CloudinaryService;
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
	private CloudinaryService cloudinaryService;

	@Autowired
	public ResumeManager(ResumeDao resumeDao, CloudinaryService cloudinaryService) {
		super();
		this.resumeDao = resumeDao;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public DataResult<List<Resume>> getAll() {
		return new SuccessDataResult<List<Resume>>(this.resumeDao.findAll());
	}

	@Override
	public Result add(Resume resume) {
		resume.setCreateDate(LocalDate.now());
		this.resumeDao.save(resume);
		return new SuccessResult();
	}

	@Override
	public Result update(Resume resume) {
		resume.setUpdateDate(LocalDate.now());
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
            return new ErrorResult("??zge??mi?? kayd?? bulunamad??");
        }else if(!githubLink.startsWith("https://github.com")){
            if(!githubLink.startsWith("github.com")){
                return new ErrorResult("Ge??erli bir github linki de??il");
            }
        }

        Resume resume = this.resumeDao.getById(resumeId);
        resume.setGithubLink(githubLink);
        resume.setUpdateDate(LocalDate.now());
        this.resumeDao.save(resume);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteGithubLink(int resumeId) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("??zge??mi?? kayd?? bulunamad??");
        }
        Resume resume = this.resumeDao.getById(resumeId);
        resume.setGithubLink(null);
        resume.setUpdateDate(LocalDate.now());
        this.resumeDao.save(resume);

        return new SuccessResult("Github adresi kald??r??ld??");
    }

    @Override
    public Result updateLinkedinLink(int resumeId, String linkedinLink) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("??zge??mi?? kayd?? bulunamad??");
        } else if(!linkedinLink.startsWith("https://www.linkedin.com") &&
                !linkedinLink.startsWith("www.linkedin.com") &&
                !linkedinLink.startsWith("https://linkedin.com") &&
                !linkedinLink.startsWith("linkedin.com")){
            return new ErrorResult("Ge??erli bir linkedin adresi de??il");
        }
        Resume resume = this.resumeDao.getById(resumeId);
        resume.setLinkedinLink(linkedinLink);
        resume.setUpdateDate(LocalDate.now());
        this.resumeDao.save(resume);
        return new SuccessResult("Kaydedildi");
    }

    @Override
    public Result deleteLinkedinLink(int resumeId) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("??zge??mi?? kayd?? bulunamad??");
        }
        Resume resume = this.resumeDao.getById(resumeId);
        resume.setLinkedinLink(null);
        resume.setUpdateDate(LocalDate.now());
        this.resumeDao.save(resume);
        return new SuccessResult("Linkedin adresi silindi");
    }

    @Override
    public Result updateObjective(int resumeId, String objective) {
        if(!this.resumeDao.existsById(resumeId)){
            return new ErrorResult("??zge??mi?? kayd?? bulunamad??");
        } else if(objective.length()<=2){
            return new ErrorResult("??n yaz?? 2 karakterden uzun olmal??d??r");
        }
        Resume resume = this.resumeDao.getById(resumeId);
        resume.setObjective(objective);
        resume.setUpdateDate(LocalDate.now());
        this.resumeDao.save(resume);
        return new SuccessResult("??n yaz?? kaydedildi");
    }

    @Override
    public Result updatePhoto(MultipartFile multipartFile, int resumeId) {
        try {
            BufferedImage bufferedImage= ImageIO.read(multipartFile.getInputStream());
            if(bufferedImage==null){
                return new ErrorResult("Resim validasyonu ba??ar??s??z");
            }

            Map result=cloudinaryService.upload(multipartFile);
            String url = (String)result.get("url");
            Resume resume = this.resumeDao.getById(resumeId);
            resume.setPhoto(url);
            resume.setUpdateDate(LocalDate.now());
            this.resumeDao.save(resume);
            return new SuccessResult("Foto??raf kaydedildi");
        }catch (IOException exception){
            return new ErrorResult("Foto??raf y??kleme a??amas??nda bir hata olu??tu.");
        }
    }

}
