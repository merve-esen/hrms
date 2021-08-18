package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

public interface EmployerUpdateService {
	DataResult<List<EmployerUpdate>> getAll();
	DataResult<EmployerUpdate> getById(int id);
	DataResult<List<EmployerUpdate>> getByEmployer_Id(int employerId);
	DataResult<List<EmployerUpdate>> getByDeletedFalseAndConfirmedFalse();
	DataResult<List<EmployerUpdate>> getByEmployer_IdAndDeletedFalse(int employerId);
	Result add(EmployerUpdate employerUpdate);
	Result update(EmployerUpdate employerUpdate);
	Result confirm(int employerUpdateId, int employeeId);
	Result reject(int employerUpdateId, int employeeId);
}
