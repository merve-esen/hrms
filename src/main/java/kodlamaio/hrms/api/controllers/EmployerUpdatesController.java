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

import kodlamaio.hrms.business.abstracts.EmployerUpdateService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.EmployerUpdate;

@RestController
@CrossOrigin
@RequestMapping("api/employerUpdates")
public class EmployerUpdatesController {
	private EmployerUpdateService employerUpdateService;

	@Autowired
	public EmployerUpdatesController(EmployerUpdateService employerUpdateService) {
		super();
		this.employerUpdateService = employerUpdateService;
	}

	@GetMapping("/getall")
	public DataResult<List<EmployerUpdate>> getAll(){
		return this.employerUpdateService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<EmployerUpdate> getById(int id){
		return this.employerUpdateService.getById(id);
	}

	@GetMapping("/getbydeletedfalse")
	public DataResult<List<EmployerUpdate>> getByDeletedFalse(){
		return this.employerUpdateService.getByDeletedFalse();
	}

	@GetMapping("/getbyemployerid")
	public DataResult<List<EmployerUpdate>> getByEmployerId(@RequestParam("employerId") int employerId){
		return this.employerUpdateService.getByEmployer_Id(employerId);
	}

	@GetMapping("/getbyemployeridanddeletedfalse")
	public DataResult<List<EmployerUpdate>> getByEmployer_IdAndDeletedFalse(@RequestParam("employerId") int employerId){
		return this.employerUpdateService.getByEmployer_IdAndDeletedFalse(employerId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody EmployerUpdate employerUpdate) {
		return this.employerUpdateService.add(employerUpdate);
	}

	@PostMapping("/update")
	public Result update(@RequestBody EmployerUpdate employerUpdate) {
		return this.employerUpdateService.update(employerUpdate);
	}

	@GetMapping("/confirm")
	public Result confirm(@RequestParam("employerUpdateId") int employerUpdateId, @RequestParam("employeeId") int employeeId) {
		return this.employerUpdateService.confirm(employerUpdateId, employeeId);
	}

	@GetMapping("/reject")
	public Result reject(@RequestParam("employerUpdateId") int employerUpdateId, @RequestParam("employeeId") int employeeId) {
		return this.employerUpdateService.reject(employerUpdateId, employeeId);
	}
}
