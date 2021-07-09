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

import kodlamaio.hrms.business.abstracts.FavoriteJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.FavoriteJobAdvertisement;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;

@RestController
@CrossOrigin
@RequestMapping("api/favoriteJobAdvertisements")
public class FavoriteJobAdvertisementsController {
	private FavoriteJobAdvertisementService favoriteJobAdvertisementService;

	@Autowired
	public FavoriteJobAdvertisementsController(FavoriteJobAdvertisementService favoriteJobAdvertisementService) {
		super();
		this.favoriteJobAdvertisementService = favoriteJobAdvertisementService;
	}

	@GetMapping("/getall")
	public DataResult<List<FavoriteJobAdvertisement>> getAll(){
		return this.favoriteJobAdvertisementService.getAll();
	}
	
	@GetMapping("/getbycandidateid")
	public DataResult<List<FavoriteJobAdvertisement>> getByCandidateId(@RequestParam("candidateId") int candidateId){
		return this.favoriteJobAdvertisementService.getByCandidate_Id(candidateId);
	}

	@PostMapping("/add")
	public Result add(@RequestBody FavoriteJobAdvertisement favoriteJobAdvertisement) {
		return this.favoriteJobAdvertisementService.add(favoriteJobAdvertisement);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody FavoriteJobAdvertisement favoriteJobAdvertisement) {
		return this.favoriteJobAdvertisementService.delete(favoriteJobAdvertisement);
	}
}
