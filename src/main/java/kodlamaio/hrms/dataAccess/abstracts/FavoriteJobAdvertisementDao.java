package kodlamaio.hrms.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.FavoriteJobAdvertisement;

public interface FavoriteJobAdvertisementDao extends JpaRepository<FavoriteJobAdvertisement, Integer>{

	List<FavoriteJobAdvertisement> getByCandidate_Id(int candidateId);
	FavoriteJobAdvertisement getByCandidate_IdAndJobAdvertisement_Id(int candidateId, int jobAdvertisementId);

}
