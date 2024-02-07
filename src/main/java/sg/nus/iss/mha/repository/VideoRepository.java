package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import sg.nus.iss.mha.model.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Integer> {
}


