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
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
	}
	
	@GetMapping("/getallsortbyapplicationdate")
	public DataResult<List<JobAdvertisement>> getAllSortByApplicationDate(){
		return this.jobAdvertisementService.findByIsActiveTrueOrderByApplicationDeadline();
	}
	
	@GetMapping("/getallsortbyapplicationdatedesc")
	public DataResult<List<JobAdvertisement>> getAllSortByApplicationDateDesc(){
		return this.jobAdvertisementService.findByIsActiveTrueOrderByApplicationDeadlineDesc();
	}
	
	@GetMapping("/getbyemployerid")
	public DataResult<List<JobAdvertisement>> getByEmployerId(@RequestParam("employerId") int employerId){
		return this.jobAdvertisementService.findByEmployer_Id(employerId);
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.add(jobAdvertisement);
	}
	
	@PostMapping("/update")
	public Result update(@RequestBody JobAdvertisement jobAdvertisement) {
		return this.jobAdvertisementService.update(jobAdvertisement);
	}
}
