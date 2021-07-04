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

import kodlamaio.hrms.business.abstracts.WorkExperienceService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkExperience;

@RestController
@CrossOrigin
@RequestMapping("api/workExperiences")
public class WorkExperiencesController {

	private WorkExperienceService workExperienceService;

	@Autowired
	public WorkExperiencesController(WorkExperienceService workExperienceService) {
		super();
		this.workExperienceService = workExperienceService;
	}
	
	@GetMapping("/getall")
	public DataResult<List<WorkExperience>> getAll(){
		return this.workExperienceService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody WorkExperience workExperience) {
		return this.workExperienceService.add(workExperience);
	}
	
	@GetMapping("/getAllByResumeIdOrderByEndYearDesc")
	public DataResult<List<WorkExperience>> getAllByResumeIdOrderByEndYearDesc(@RequestParam int resumeId){
		return this.workExperienceService.getAllByResumeIdOrderByEndYearDesc(resumeId);
	}
	
	@GetMapping("/getAllByResumeId")
	public DataResult<List<WorkExperience>> getAllByResumeId(@RequestParam int resumeId){
		return this.workExperienceService.getAllByResumeId(resumeId);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody WorkExperience workExperience) {
		return this.workExperienceService.delete(workExperience);
	}
}
