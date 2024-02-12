package sg.nus.iss.mha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sg.nus.iss.mha.model.DiabetesData;
import sg.nus.iss.mha.model.DiabetesDataPK;
import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.model.HeartDiseaseDataPK;

public interface DiabetesDataRepository extends JpaRepository<DiabetesData, DiabetesDataPK> {

	@Query("SELECT d FROM DiabetesData d WHERE d.id.userId = :userId ORDER BY d.id.date DESC")
    Page<DiabetesData> findLatestByUserId(Integer userId, Pageable pageable);
}

