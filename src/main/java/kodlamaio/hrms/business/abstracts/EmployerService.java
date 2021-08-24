package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

public interface EmployerService {
	DataResult<List<Employer>> getAll();
	DataResult<Employer> getById(int id);
	DataResult<List<Employer>> getByConfirmedTrue();
	DataResult<List<Employer>> getByConfirmedFalse();
	Result add(Employer employer);
	Result confirm(int employerId, int employeeId);
	Result reject(int employerId, int employeeId);
}
