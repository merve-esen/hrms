package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavoriteJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.FavoriteJobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.FavoriteJobAdvertisement;

@Service
public class FavoriteJobAdvertisementManager implements FavoriteJobAdvertisementService{
	private FavoriteJobAdvertisementDao favoriteJobAdvertisementDao;

	@Autowired
	public FavoriteJobAdvertisementManager(FavoriteJobAdvertisementDao favoriteJobAdvertisementDao) {
		super();
		this.favoriteJobAdvertisementDao = favoriteJobAdvertisementDao;
	}

	@Override
	public DataResult<List<FavoriteJobAdvertisement>> getAll() {
		return new SuccessDataResult<List<FavoriteJobAdvertisement>>(this.favoriteJobAdvertisementDao.findAll());
	}
	
	@Override
	public DataResult<List<FavoriteJobAdvertisement>> getByCandidate_Id(int candidateId) {
		return new SuccessDataResult<List<FavoriteJobAdvertisement>>(this.favoriteJobAdvertisementDao.getByCandidate_Id(candidateId));
	}

	@Override
	public Result add(FavoriteJobAdvertisement favoriteJobAdvertisement) {
		FavoriteJobAdvertisement favJobAdvertisement=this.favoriteJobAdvertisementDao.getByCandidate_IdAndJobAdvertisement_Id
				(favoriteJobAdvertisement.getCandidate().getId(), favoriteJobAdvertisement.getJobAdvertisement().getId());
		if(favJobAdvertisement != null)
			return new ErrorResult("Bu ilan zaten favorilere eklenmiş");
		
		this.favoriteJobAdvertisementDao.save(favoriteJobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result delete(FavoriteJobAdvertisement favoriteJobAdvertisement) {
		FavoriteJobAdvertisement favJobAdvertisement=this.favoriteJobAdvertisementDao.getByCandidate_IdAndJobAdvertisement_Id
				(favoriteJobAdvertisement.getCandidate().getId(), favoriteJobAdvertisement.getJobAdvertisement().getId());
		if(favJobAdvertisement != null) {
			this.favoriteJobAdvertisementDao.delete(favJobAdvertisement);
			return new SuccessResult();
		}
		else {
			return new ErrorResult("Bu ilan zaten favorilerden kaldırılmış");
		}
	}
}
