package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.mha.model.Submission;

public interface SubmissionService {
	   
	   Optional<Submission> findSubmission(int id);
	   
	   Submission createSubmission(Submission inSubmission);

	   List<Submission> findAllSubmission();

	   void deleteSubmission(int id);
	   



	   }