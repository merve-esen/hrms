package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.LanguageSkillService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.LanguageSkill;

@RestController
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
	
	@PostMapping("/add")
	public Result add(@RequestBody LanguageSkill languageSkill) {
		return this.languageSkillService.add(languageSkill);
	}
}
