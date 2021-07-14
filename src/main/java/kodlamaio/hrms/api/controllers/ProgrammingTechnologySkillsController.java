package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.ProgrammingTechnologySkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ProgrammingTechnologySkill;

@RestController
@CrossOrigin
@RequestMapping("api/programmingTechnologySkills")
public class ProgrammingTechnologySkillsController {

	private ProgrammingTechnologySkillService programmingTechnologySkillService;

	@Autowired
	public ProgrammingTechnologySkillsController(ProgrammingTechnologySkillService programmingTechnologySkillService) {
		super();
		this.programmingTechnologySkillService = programmingTechnologySkillService;
	}

	@GetMapping("/getall")
	public DataResult<List<ProgrammingTechnologySkill>> getAll(){
		return this.programmingTechnologySkillService.getAll();
	}

	@GetMapping("/getAllByResumeId")
	public DataResult<List<ProgrammingTechnologySkill>> getAllByResumeId(@RequestParam int resumeId){
		return this.programmingTechnologySkillService.getAllByResumeId(resumeId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody ProgrammingTechnologySkill programmingTechnologySkill) {
		return this.programmingTechnologySkillService.add(programmingTechnologySkill);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int programmingTechnologySkillId) {
		return this.programmingTechnologySkillService.delete(programmingTechnologySkillId);
	}
}
