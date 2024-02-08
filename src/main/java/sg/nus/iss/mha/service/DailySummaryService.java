package sg.nus.iss.mha.service;

import java.util.List;

import sg.nus.iss.mha.model.DailySummary;

public interface DailySummaryService {

	List<DailySummary> findAllDailySummary();

	DailySummary saveDailySummary(DailySummary inDailySummary);

   



   }