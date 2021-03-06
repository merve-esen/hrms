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

import kodlamaio.hrms.business.abstracts.LanguageSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageSkill;

@RestController
@CrossOrigin
@RequestMapping("api/languageSkills")
public class LanguageSkillsController {

	private LanguageSkillService languageSkillService;

	@Autowired
	public LanguageSkillsController(LanguageSkillService languageSkillService) {
		super();
		this.languageSkillService = languageSkillService;
	}

	@GetMapping("/getall")
	public DataResult<List<LanguageSkill>> getAll(){
		return this.languageSkillService.getAll();
	}

	@GetMapping("/getAllByResumeId")
	public DataResult<List<LanguageSkill>> getAllByResumeId(@RequestParam int resumeId){
		return this.languageSkillService.getAllByResumeId(resumeId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody LanguageSkill languageSkill) {
		return this.languageSkillService.add(languageSkill);
	}

	@DeleteMapping("/delete")
	public Result delete(@RequestParam int languageSkillId) {
		return this.languageSkillService.delete(languageSkillId);
	}
}
