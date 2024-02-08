package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import sg.nus.iss.mha.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
    @Query("SELECT v FROM Video v WHERE v.type = :type")
    List<Video> findByType(@Param("type") Integer type);
}


