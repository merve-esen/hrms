package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.FavoriteJobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
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
	public Result add(FavoriteJobAdvertisement favoriteJobAdvertisement) {
		this.favoriteJobAdvertisementDao.save(favoriteJobAdvertisement);
		return new SuccessResult();
	}

	@Override
	public Result delete(FavoriteJobAdvertisement favoriteJobAdvertisement) {
		this.favoriteJobAdvertisementDao.delete(favoriteJobAdvertisement);
		return new SuccessResult();
	}
}
