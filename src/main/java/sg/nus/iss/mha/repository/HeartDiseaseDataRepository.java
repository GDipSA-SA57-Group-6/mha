package sg.nus.iss.mha.repository;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.model.HeartDiseaseDataPK;






public interface HeartDiseaseDataRepository extends JpaRepository<HeartDiseaseData, HeartDiseaseDataPK> {
	Optional<HeartDiseaseData> findById_UserIdAndId_Date(Integer userId, LocalDate date);
	 @Query("SELECT h FROM HeartDiseaseData h WHERE h.id.userId = :userId ORDER BY h.id.date DESC")
	    Page<HeartDiseaseData> findLatestByUserId(Integer userId, Pageable pageable);
	}





