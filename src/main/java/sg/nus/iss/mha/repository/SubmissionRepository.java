package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.mha.model.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Integer>{}
