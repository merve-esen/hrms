package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employer;

@RestController
@CrossOrigin
@RequestMapping("api/employers")
public class EmployersController {

	private EmployerService employerService;

	@Autowired
	public EmployersController(EmployerService employerService) {
		super();
		this.employerService = employerService;
	}

	@GetMapping("/getall")
	public DataResult<List<Employer>> getAll(){
		return this.employerService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<Employer> getById(int id){
		return this.employerService.getById(id);
	}

	@GetMapping("/getbyconfirmedtrue")
	public DataResult<List<Employer>> getByConfirmedTrue(){
		return this.employerService.getByConfirmedTrue();
	}

	@GetMapping("/getbyconfirmedfalse")
	public DataResult<List<Employer>> getByConfirmedFalse(){
		return this.employerService.getByConfirmedFalse();
	}

	@GetMapping("/getbyconfirmedisnull")
	public DataResult<List<Employer>> getByConfirmedIsNull(){
		return this.employerService.getByConfirmedIsNull();
	}

	@PostMapping("/add")
	public Result add(@RequestBody Employer employer) {
		return this.employerService.add(employer);
	}

	@GetMapping("/confirm")
	public Result confirm(@RequestParam("employerId") int employerId, @RequestParam("employeeId") int employeeId) {
		return this.employerService.confirm(employerId, employeeId);
	}

	@GetMapping("/reject")
	public Result reject(@RequestParam("employerId") int employerId, @RequestParam("employeeId") int employeeId) {
		return this.employerService.reject(employerId, employeeId);
	}
}