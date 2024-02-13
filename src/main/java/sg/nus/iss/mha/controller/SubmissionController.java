package sg.nus.iss.mha.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.DailySummary;
import sg.nus.iss.mha.model.Submission;
import sg.nus.iss.mha.model.SubmissionItem;
import sg.nus.iss.mha.repository.DailySummaryRepository;
import sg.nus.iss.mha.service.DailySummaryService;
import sg.nus.iss.mha.service.SubmissionService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class SubmissionController {
	@Autowired
	private SubmissionService submissionService;
	@Autowired
	private DailySummaryService dailySummaryService;
	@Autowired
	private DailySummaryRepository dailySummaryRepository;
	
    //Get: http://localhost:8080/api/submission-by-userid/2
    @GetMapping("/get-submission-by-userid/{userId}")
    public ResponseEntity<Map<String, Integer>> getSubmissionByUserId(@PathVariable Integer userId) {
        try {
            LocalDate today = LocalDate.now();

            Optional<DailySummary> dailySummaryOptional = dailySummaryRepository.findByUserIdAndDate(userId, today);

            // Create a map to store the results
            Map<String, Integer> result = new HashMap<>();

            // Check if DailySummary is present in the Optional
            if (dailySummaryOptional.isPresent()) {
                DailySummary dailySummary = dailySummaryOptional.get();

                // Populate the map with the values from DailySummary
                result.put("cal_sum", dailySummary.getCal_sum());
                result.put("protein_sum", dailySummary.getProtein_sum());
                result.put("fat_sum", dailySummary.getFat_sum());
                result.put("carb_sum", dailySummary.getCarb_sum());
            } else {
                // If no DailySummary is found, set default values to 0
                result.put("cal_sum", 0);
                result.put("protein_sum", 0);
                result.put("fat_sum", 0);
                result.put("carb_sum", 0);
            }

            // Return the map as a JSON response
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyMap());
        }
    }
	
	@GetMapping("/submission")
	public List<Submission> getAllSubmission(){
		return submissionService.findAllSubmission();
	}
	
	@PostMapping("/submission")
    public ResponseEntity<String> createSubmission(@RequestBody Submission inSubmission) {
        try {
            // Log the received data for debugging
//            System.out.println("Received UserId: " + inSubmission.getUserId());
//            System.out.println("Received Date: " + inSubmission.getDate());

            // Your submissionService logic here to handle the JSON data
        	
        	for (SubmissionItem item: inSubmission.getSubmissionItems())
            {
        		item.setSubmission(inSubmission);
            }
            Submission retSubmission = submissionService.createSubmission(inSubmission);
            
            Date today = new Date();
            today.setHours(0);
            LocalDate todayLocalDate = today.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            
            List<DailySummary> allDailySummaries = dailySummaryService.findAllDailySummary();
            DailySummary dailySummary = null;
            for (DailySummary item: allDailySummaries)
            {
            	if (item.getDate().equals(todayLocalDate) && item.getUserId() == inSubmission.getUserId())
            	{
            		dailySummary = item;
            		break;
            	}
            }
            
            if (dailySummary == null)
            {
            	dailySummary = new DailySummary();
            	dailySummary.setUserId(inSubmission.getUserId());
            	dailySummary.setDate(todayLocalDate);
                //dailySummaryService.createDailySummary(dailySummary);
            }
            		
            for (SubmissionItem submissionItem: inSubmission.getSubmissionItems())
            {
            	dailySummary.setCal_sum(dailySummary.getCal_sum() + submissionItem.getFood().getCal());
            	dailySummary.setProtein_sum(dailySummary.getProtein_sum() + submissionItem.getFood().getProtein());
            	dailySummary.setFat_sum(dailySummary.getFat_sum() + submissionItem.getFood().getFat());
            	dailySummary.setCarb_sum(dailySummary.getCarb_sum() + submissionItem.getFood().getCab());
            }
            
            dailySummaryService.saveDailySummary(dailySummary);
            
            // Log the created item for debugging
            System.out.println("Created Item: " + retSubmission);

            return new ResponseEntity<>("Item created successfully", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to create item", HttpStatus.EXPECTATION_FAILED);
        }
    }
}


