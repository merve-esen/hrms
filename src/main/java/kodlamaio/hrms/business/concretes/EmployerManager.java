package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.entities.concretes.Employee;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements EmployerService{

	private EmployerDao employerDao;

	@Autowired
	public EmployerManager(EmployerDao employerDao) {
		super();
		this.employerDao = employerDao;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.findAll());
	}

	@Override
	public DataResult<Employer> getById(int id) {
		return new SuccessDataResult<Employer>(this.employerDao.getById(id));
	}

	@Override
	public DataResult<List<Employer>> getByConfirmedTrue() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByConfirmedTrue());
	}

	@Override
	public DataResult<List<Employer>> getByConfirmedFalse() {
		return new SuccessDataResult<List<Employer>>(this.employerDao.getByConfirmedFalse());
	}

	@Override
	public Result add(Employer employer) {

		if(employer.getCompanyName() == null || employer.getWebSite() == null || employer.getPhoneNumber() == null ||
				employer.getEmail() == null || employer.getPassword() == null)
			return new ErrorResult("Tüm alanlar zorunludur.");

		if(!isRealEmployer(employer)) {
			return new ErrorResult("Geçersiz Email Adresi");
		}

		this.employerDao.save(employer);
		return new SuccessResult();
	}

	@Override
	public Result confirm(int employerId, int employeeId) {
		Employer employer=this.employerDao.getById(employerId);
		if (employer == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		employer.setConfirmed(true);
		employer.setConfirmDate(LocalDate.now());
		employer.setEmployee(employee);

		this.employerDao.save(employer);
		return new SuccessResult();
	}

	@Override
	public Result reject(int employerId, int employeeId) {
		Employer employer=this.employerDao.getById(employerId);
		if (employer == null)
			return new ErrorResult("Kayıt bulunamadı");

		Employee employee = new Employee();
		employee.setId(employeeId);

		employer.setConfirmed(false);
		employer.setConfirmDate(LocalDate.now());
		employer.setEmployee(employee);

		this.employerDao.save(employer);
		return new SuccessResult();
	}

	private boolean isRealEmployer(Employer employer) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(employer.getEmail());
	     if(!matcher.matches()) {
	    	 return false;
	     }
	     else if(!employer.getEmail().contains(employer.getWebSite())) {
	    	return false;
	     }
	     return true;

	}
}
