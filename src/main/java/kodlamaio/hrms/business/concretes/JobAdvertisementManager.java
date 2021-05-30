package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{
	
	private JobAdvertisementDao jobAdvertisementDao;

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll());
	}

	@Override
	public Result add(JobAdvertisement jobAdvertisement) {
		if(jobAdvertisement.getJobPosition() == null)
			return new ErrorResult("Genel iş pozisyonu seçimi zorunludur.");
		if(jobAdvertisement.getJobDescription() == null)
			return new ErrorResult("İş tanımı alanı zorunludur.");
		if(jobAdvertisement.getCity() == null)
			return new ErrorResult("Şehir seçimi zorunludur.");
		if(jobAdvertisement.getNumberOfOpenPositions() == 0)
			return new ErrorResult("Açık pozisyon adedi alanı zorunludur.");
		
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

}
