package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
	@PostMapping("/add")
	public Result add(@RequestBody ProgrammingTechnologySkill programmingTechnologySkill) {
		return this.programmingTechnologySkillService.add(programmingTechnologySkill);
	}
	
	@PostMapping("/delete")
	public Result delete(@RequestBody ProgrammingTechnologySkill programmingTechnologySkill) {
		return this.programmingTechnologySkillService.delete(programmingTechnologySkill);
	}
}
