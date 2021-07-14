package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserCheckService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.dataAccess.abstracts.ResumeDao;
import kodlamaio.hrms.entities.concretes.Candidate;
import kodlamaio.hrms.entities.concretes.Resume;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private ResumeDao resumeDao;
	private UserCheckService userCheckService;

	@Autowired
	public CandidateManager(CandidateDao candidateDao, ResumeDao resumeDao, UserCheckService userCheckService) {
		super();
		this.candidateDao = candidateDao;
		this.resumeDao = resumeDao;
		this.userCheckService = userCheckService;
	}

	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(this.candidateDao.findAll());
	}

	@Override
	public Result add(Candidate candidate) {

		if(candidate.getFirstName() == null || candidate.getLastName() == null || candidate.getIdentityNumber() == null ||
				candidate.getBirthYear() == 0 ||  candidate.getEmail() == null || candidate.getPassword() == null)
			return new ErrorResult("Tüm alanlar zorunludur.");

		if(!this.userCheckService.checkIfRealPerson(candidate.getIdentityNumber(), candidate.getFirstName(),
				candidate.getLastName(), candidate.getBirthYear()).isSuccess())
			return new ErrorResult("Mernis doğrulaması başarısız oldu.");

		if (this.candidateDao.existsByEmail(candidate.getEmail())){
			return new ErrorResult("Bu email adresine ait bir kayıt bulunmaktadır.");
		}

		if (this.candidateDao.existsByIdentityNumber(candidate.getIdentityNumber())){
			return new ErrorResult("Bu kimlik numarasına ait bir kayıt bulunmaktadır.");
		}

		this.candidateDao.save(candidate);
		if(candidate.getId()>0) {
			Resume resume = new Resume();
			resume.setCandidate(candidate);
			resume.setCreateDate(LocalDate.now());
			this.resumeDao.save(resume);
		}
		return new SuccessResult();
	}

}
