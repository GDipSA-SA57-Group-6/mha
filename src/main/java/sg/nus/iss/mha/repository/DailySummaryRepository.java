package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.mha.model.DailySummary;

public interface DailySummaryRepository extends JpaRepository<DailySummary, Integer>{}

