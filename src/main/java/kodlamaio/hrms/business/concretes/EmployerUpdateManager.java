package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerUpdateDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

@Service
public class EmployerUpdateManager implements EmployerUpdateService{

	private EmployerUpdateDao employerUpdateDao;
	private EmployerDao employerDao;

	@Autowired
	public EmployerUpdateManager(EmployerUpdateDao employerUpdateDao, EmployerDao employerDao) {
		super();
		this.employerUpdateDao = employerUpdateDao;
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<EmployerUpdate>> getAll() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.findAll());
	}

	@Override
	public DataResult<EmployerUpdate> getById(int id) {
		return new SuccessDataResult<EmployerUpdate>(this.employerUpdateDao.getById(id));
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByDeletedFalse() {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getByDeletedFalse());
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByEmployer_Id(int employerId) {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getByEmployer_Id(employerId));
	}

	@Override
	public DataResult<List<EmployerUpdate>> getByEmployer_IdAndDeletedFalse(int employerId) {
		return new SuccessDataResult<List<EmployerUpdate>>(this.employerUpdateDao.getByEmployer_IdAndDeletedFalse(employerId));
	}

	@Override
	public Result add(EmployerUpdate employerUpdate) {
		if(employerUpdate.getCompanyName() == null)
			return new ErrorResult("Şirket adı zorunludur.");
		if(employerUpdate.getPhoneNumber() == null)
			return new ErrorResult("Telefon numarası zorunludur.");
		if(employerUpdate.getWebSite() == null)
			return new ErrorResult("Web sitesi zorunludur.");
		if(this.employerUpdateDao.getByEmployer_IdAndDeletedFalse(employerUpdate.getEmployer().getId()).size() != 0)
			return new ErrorResult("Aktif bir güncelleme talebi mevcut. Kaydetme işlemi yapılmadı.");

		employerUpdate.setDeleted(false);
		employerUpdate.setCreateDate(LocalDate.now());

		this.employerUpdateDao.save(employerUpdate);
		return new SuccessResult();
	}

	@Override
	public Result update(EmployerUpdate employerUpdate) {
		this.employerUpdateDao.save(employerUpdate);
		return new SuccessResult();
	}

	@Override
	public Result confirm(int employerUpdateId, int employeeId) {
		EmployerUpdate employerUpdate = this.employerUpdateDao.getById(employerUpdateId);
		if (employerUpdate == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employer employer = this.employerDao.getById(employerUpdate.getEmployer().getId());
		if (employer == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		employerUpdate.setDeleted(true);
		employerUpdate.setConfirmed(true);
		employerUpdate.setConfirmDate(LocalDate.now());
		employerUpdate.setEmployee(employee);
		this.employerUpdateDao.save(employerUpdate);

		employer.setCompanyName(employerUpdate.getCompanyName());
		employer.setPhoneNumber(employerUpdate.getPhoneNumber());
		employer.setWebSite(employerUpdate.getWebSite());
		this.employerDao.save(employer);

		return new SuccessResult();
	}

	@Override
	public Result reject(int employerUpdateId, int employeeId) {
		EmployerUpdate employerUpdate=this.employerUpdateDao.getById(employerUpdateId);
		if (employerUpdate == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		employerUpdate.setDeleted(true);
		employerUpdate.setConfirmed(false);
		employerUpdate.setConfirmDate(LocalDate.now());
		employerUpdate.setEmployee(employee);

		this.employerUpdateDao.save(employerUpdate);
		return new SuccessResult();
	}

}
