package sg.nus.iss.mha.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import sg.nus.iss.mha.model.DailySummary;


public interface DailySummaryService {
	
	Optional<DailySummary> findByUserIdAndDate(int userId, LocalDate date);;

	List<DailySummary> findAllDailySummary();

	DailySummary saveDailySummary(DailySummary inDailySummary);

   



   }