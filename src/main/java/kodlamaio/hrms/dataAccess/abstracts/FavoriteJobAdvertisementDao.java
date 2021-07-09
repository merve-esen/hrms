package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.FavoriteJobAdvertisement;

public interface FavoriteJobAdvertisementDao extends JpaRepository<FavoriteJobAdvertisement, Integer>{

}
