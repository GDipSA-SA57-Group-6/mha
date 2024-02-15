package sg.nus.iss.mha.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mha.model.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    @Query("SELECT a FROM Advertisement a WHERE a.type=:type")
    List<Advertisement> findAdvByType(@Param("type")Integer type);

}
