package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import sg.nus.iss.mha.model.Submission;
import sg.nus.iss.mha.repository.SubmissionRepository;
import sg.nus.iss.mha.service.SubmissionService;

@Service
@Transactional(readOnly = true)
public class SubmissionServiceImpl implements SubmissionService {

    @Resource
    private SubmissionRepository submissionRepository; // Corrected variable name

    @Override
    public List<Submission> findAllSubmission() {
        return submissionRepository.findAll();
    }

    @Transactional(readOnly = false)
    @Override
    public Submission createSubmission(Submission submission) {
        return submissionRepository.save(submission);
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteSubmission(int id) {
        submissionRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Submission> findSubmission(int id) {
        return submissionRepository.findById(id);
    }
}

