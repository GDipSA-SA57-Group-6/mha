package sg.nus.iss.mha.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import sg.nus.iss.mha.model.DailySummary;
import sg.nus.iss.mha.repository.DailySummaryRepository;
import sg.nus.iss.mha.service.DailySummaryService;

@Service
@Transactional(readOnly = true)
public class DailySummaryServiceImpl implements DailySummaryService {

    @Resource
    private DailySummaryRepository dailySummaryRepository;

	@Override
	public List<DailySummary> findAllDailySummary() {
		// TODO Auto-generated method stub
		return dailySummaryRepository.findAll();
	}

	@Transactional(readOnly = false)
	@Override
	public DailySummary saveDailySummary(DailySummary inDailySummary) {
		// TODO Auto-generated method stub
		return dailySummaryRepository.save(inDailySummary);

	}

	@Override
	public Optional<DailySummary> findByUserIdAndDate(int userId, LocalDate date) {
		return dailySummaryRepository.findByUserIdAndDate(userId, date);
	}
  
}