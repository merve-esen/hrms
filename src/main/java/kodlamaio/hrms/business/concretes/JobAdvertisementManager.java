package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

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
	public DataResult<JobAdvertisement> getById(int id) {
		return new SuccessDataResult<JobAdvertisement>(this.jobAdvertisementDao.getById(id));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActiveTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadline() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActiveTrueOrderByApplicationDeadline());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadlineDesc() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActiveTrueOrderByApplicationDeadlineDesc());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployer_Id(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByEmployer_Id(employerId));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployer_IdAndIsActiveTrue(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByEmployer_IdAndIsActiveTrue(employerId));
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByConfirmedTrue() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByConfirmedTrue());
	}

	@Override
	public DataResult<List<JobAdvertisement>> getByEmployer_IdAndConfirmedTrue(int employerId) {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByEmployer_IdAndConfirmedTrue(employerId));
	}

	@Override
    public DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdvertisementFilter jobAdvertisementFilter) {
        Pageable pageable = PageRequest.of(pageNo -1, pageSize);
        return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByFilter(jobAdvertisementFilter, pageable).getContent(),
        		this.jobAdvertisementDao.getByFilter(jobAdvertisementFilter,pageable).getTotalElements()+"");
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

		jobAdvertisement.setActive(true);
		jobAdvertisement.setCreateDate(LocalDate.now());

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result update(JobAdvertisement jobAdvertisement) {
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result close(int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement=this.jobAdvertisementDao.getById(jobAdvertisementId);
		if (jobAdvertisement == null)
			return new ErrorResult("Kayıt bulunamadı");

		jobAdvertisement.setActive(false);

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result publish(int jobAdvertisementId) {
		JobAdvertisement jobAdvertisement=this.jobAdvertisementDao.getById(jobAdvertisementId);
		if (jobAdvertisement == null)
			return new ErrorResult("Kayıt bulunamadı");

		jobAdvertisement.setActive(true);

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result confirm(int jobAdvertisementId, int employeeId) {
		JobAdvertisement jobAdvertisement=this.jobAdvertisementDao.getById(jobAdvertisementId);
		if (jobAdvertisement == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		jobAdvertisement.setConfirmed(true);
		jobAdvertisement.setConfirmDate(LocalDate.now());
		jobAdvertisement.setEmployee(employee);

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result reject(int jobAdvertisementId, int employeeId) {
		JobAdvertisement jobAdvertisement=this.jobAdvertisementDao.getById(jobAdvertisementId);
		if (jobAdvertisement == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		jobAdvertisement.setConfirmed(false);
		jobAdvertisement.setConfirmDate(LocalDate.now());
		jobAdvertisement.setEmployee(employee);

		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult();
	}

}
