package sg.nus.iss.mha.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import sg.nus.iss.mha.model.SubmissionItem;

@Entity
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    @Column(name = "submit_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    
    @OneToMany(mappedBy = "submission", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    private List<SubmissionItem> submissionItems;

    // Default constructor
    public Submission() {
    }

    // Parameterized constructor
    public Submission(int userId, LocalDate date) {
        this.userId = userId;
        this.date = date;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void addSubmissionItem(SubmissionItem submissionItem) {
		if (submissionItems == null)
			submissionItems = new ArrayList<SubmissionItem>();
		submissionItems.add(submissionItem);
	}

	public List<SubmissionItem> getSubmissionItems() {
		return this.submissionItems;
	}

	public List<SubmissionItem> setSubmissionItems(List<SubmissionItem> items) {
		return this.submissionItems = items;
	}

    // Getters and setters (omitted for brevity)
}
