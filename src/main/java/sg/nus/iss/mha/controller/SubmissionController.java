package sg.nus.iss.mha.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.DailySummary;
import sg.nus.iss.mha.model.Submission;
import sg.nus.iss.mha.model.SubmissionItem;
import sg.nus.iss.mha.service.DailySummaryService;
import sg.nus.iss.mha.service.SubmissionService;

@RestController
@RequestMapping("/api")
public class SubmissionController {
	@Autowired
	private SubmissionService submissionService;
	@Autowired
	private DailySummaryService dailySummaryService;
	
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
            	if (item.getDate().equals(todayLocalDate) && item.getUserid() == inSubmission.getUserId())
            	{
            		dailySummary = item;
            		break;
            	}
            }
            
            if (dailySummary == null)
            {
            	dailySummary = new DailySummary();
            	dailySummary.setUserid(inSubmission.getUserId());
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
            }}}