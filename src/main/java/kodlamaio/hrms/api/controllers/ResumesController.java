package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.ResumeService;
import kodlamaio.hrms.core.services.CloudinaryService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Resume;

@RestController
@CrossOrigin
@RequestMapping("api/resumes")
public class ResumesController {

	private ResumeService resumeService;
	private CloudinaryService cloudinaryService;

	@Autowired
	public ResumesController(ResumeService resumeService, CloudinaryService cloudinaryService) {
		super();
		this.resumeService = resumeService;
		this.cloudinaryService = cloudinaryService;
	}

	@GetMapping("/getall")
	public DataResult<List<Resume>> getAll(){
		return this.resumeService.getAll();
	}

	@PostMapping("/add")
	public Result add(@RequestBody Resume resume) {
		return this.resumeService.add(resume);
	}

	@PostMapping("/update")
	public Result update(@RequestBody Resume resume) {
		return this.resumeService.update(resume);
	}

	@GetMapping("/getByCandidateId")
	public DataResult<Resume>  getByCandidateId(@RequestParam int candidateId){
		return this.resumeService.getByCandidate_Id(candidateId);
	}

	@PutMapping("/updateGithubLink")
    public ResponseEntity<?> updateGithub(@RequestParam int resumeId, @RequestParam String githubLink){
        Result result=this.resumeService.updateGithubLink(resumeId, githubLink);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("/deleteGithubLink")
    public ResponseEntity<?> deleteGithub(@RequestParam int resumeId){
        Result result=this.resumeService.deleteGithubLink(resumeId);
        if (result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateLinkedinLink")
    public ResponseEntity<?> updateLinkedinLink(@RequestParam int resumeId, @RequestParam String linkedinLink){
        Result result=this.resumeService.updateLinkedinLink(resumeId, linkedinLink);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @DeleteMapping("deleteLinkedinLink")
    public ResponseEntity<?> deleteLinkedinLink(@RequestParam int resumeId){
        Result result=this.resumeService.deleteLinkedinLink(resumeId);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PutMapping("/updateObjective")
    public ResponseEntity<?> updateObjective(@RequestParam int resumeId, @RequestParam String objective){
        Result result=this.resumeService.updateObjective(resumeId, objective);
        if(result.isSuccess()){
            return ResponseEntity.ok(result);
        }
        return ResponseEntity.badRequest().body(result);
    }

    @PostMapping("/updatePhoto")
    public ResponseEntity<?> updatePhoto(@RequestParam MultipartFile multipartFile, @RequestParam int resumeId){
        Result result=this.resumeService.updatePhoto(multipartFile, resumeId);
        if(!result.isSuccess()){
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

}
