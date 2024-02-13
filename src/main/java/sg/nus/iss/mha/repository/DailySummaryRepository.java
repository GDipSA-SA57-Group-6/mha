package sg.nus.iss.mha.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.mha.model.DailySummary;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Integer>{
	Optional<DailySummary> findByUserIdAndDate(int userId, LocalDate date);
	
}

