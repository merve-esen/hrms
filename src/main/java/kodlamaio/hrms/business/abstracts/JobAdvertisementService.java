package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementFilter;

public interface JobAdvertisementService {
	DataResult<JobAdvertisement> getById(int id);
	DataResult<List<JobAdvertisement>> getAll();
	DataResult<List<JobAdvertisement>> getByIsActiveTrue();
	DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadline();
	DataResult<List<JobAdvertisement>> getByIsActiveTrueOrderByApplicationDeadlineDesc();
	DataResult<List<JobAdvertisement>> getByEmployer_Id(int employerId);
	DataResult<List<JobAdvertisement>> getByEmployer_IdAndIsActiveTrue(int employerId);
	DataResult<List<JobAdvertisement>> getByConfirmedIsNull();
	DataResult<List<JobAdvertisement>> getByConfirmedTrue();
	DataResult<List<JobAdvertisement>> getByEmployer_IdAndConfirmedTrue(int employerId);
	DataResult<List<JobAdvertisement>> getByIsActiveAndPageNumberAndFilter(int pageNo, int pageSize, JobAdvertisementFilter jobAdFilter);
	Result add(JobAdvertisement jobAdvertisement);
	Result update(JobAdvertisement jobAdvertisement);
	Result publish(int jobAdvertisementId);
	Result close(int jobAdvertisementId);
	Result confirm(int jobAdvertisementId, int employeeId);
	Result reject(int jobAdvertisementId, int employeeId);
}
