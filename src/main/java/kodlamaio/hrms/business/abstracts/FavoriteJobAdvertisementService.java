package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.FavoriteJobAdvertisement;

public interface FavoriteJobAdvertisementService {
	DataResult<List<FavoriteJobAdvertisement>> getAll();
	DataResult<List<FavoriteJobAdvertisement>> getByCandidate_Id(int candidateId);
	Result add(FavoriteJobAdvertisement favoriteJobAdvertisement);
	Result delete(FavoriteJobAdvertisement favoriteJobAdvertisement);
}
