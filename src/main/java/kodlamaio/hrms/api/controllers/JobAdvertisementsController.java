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

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@CrossOrigin
@RequestMapping("api/jobAdvertisements")
public class JobAdvertisementsController {
	private JobAdvertisementService jobAdvertisementService;

	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService) {
		super();
		this.jobAdvertisementService = jobAdvertisementService;
	}

	@GetMapping("/getbyid")
	public DataResult<JobAdvertisement> getById(int id){
		return this.jobAdvertisementService.getById(id);
	}

	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}

	@GetMapping("/getbyisactivetrue")
	public DataResult<List<JobAdvertisement>> getByIsActiveTrue(){
		return this.jobAdvertisementService.getByIsActiveTrue();
	}

	@GetMapping("/getallsortbyapplicationdate")
	public DataResult<List<JobAdvertisement>> getAllSortByApplicationDate(){
		return this.jobAdvertisementService.getByIsActiveTrueOrderByApplicationDeadline();
	}

	@GetMapping("/getallsortbyapplicationdatedesc")
	public DataResult<List<JobAdvertisement>> getAllSortByApplicationDateDesc(){
		return this.jobAdvertisementService.getByIsActiveTrueOrderByApplicationDeadlineDesc();
	}

	@GetMapping("/getbyemployerid")
	public DataResult<List<JobAdvertisement>> getByEmployerId(@RequestParam("employerId") int employerId){
		return this.jobAdvertisementService.getByEmployer_Id(employerId);
	}

	@GetMapping("/getbyemployeridandisactivetrue")
	public DataResult<List<JobAdvertisement>> getByEmployerIdAndIsActiveTrue(@RequestParam("employerId") int employerId){
		return this.jobAdvertisementService.getByEmployer_IdAndIsActiveTrue(employerId);
	}

	@GetMapping("/getbyconfirmedtrue")
	public DataResult<List<JobAdvertisement>> getByConfirmedTrue(){
		return this.jobAdvertisementService.getByConfirmedTrue();
	}

	@GetMapping("/getbyemployeridandconfirmedtrue")
	public DataResult<List<JobAdvertisement>> getByEmployer_IdAndConfirmedTrue(@RequestParam("employerId") int employerId){
		return this.jobAdvertisementService.getByEmployer_IdAndConfirmedTrue(employerId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}

	@PostMapping("/update")
	public Result update(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.update(jobAdvertisement);
	}

	@GetMapping("/close")
	public Result close(@RequestParam("jobAdvertisementId") int jobAdvertisementId) {
		return this.jobAdvertisementService.close(jobAdvertisementId);
	}

	@GetMapping("/publish")
	public Result publish(@RequestParam("jobAdvertisementId") int jobAdvertisementId) {
		return this.jobAdvertisementService.publish(jobAdvertisementId);
	}

	@GetMapping("/confirm")
	public Result confirm(@RequestParam("jobAdvertisementId") int jobAdvertisementId, @RequestParam("employeeId") int employeeId) {
		return this.jobAdvertisementService.confirm(jobAdvertisementId, employeeId);
	}
}
