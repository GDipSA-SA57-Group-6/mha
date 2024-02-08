package sg.nus.iss.mha.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import sg.nus.iss.mha.model.Food;
import sg.nus.iss.mha.model.Submission;

@Entity
public class SubmissionItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//private int submissionId;
	//private int foodId;
	private int times;

	@ManyToOne
	private Submission submission;
	@ManyToOne
	private Food food;

	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}

	public SubmissionItem() {}
	/*
	public SubmissionItem(int submissionId,int foodId,int times) {
		this.submissionId=submissionId;
		this.foodId=foodId;
		this.times = times;
	
		
	}
	*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Food getFood() {
		return this.food;
	}
	
/*
	public int getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(int submissionId) {
		this.submissionId = submissionId;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
*/
	public int getTimes() {
		return times;
	}

	public void setTimes(int times) {
		this.times = times;
	}

	@Override
	public String toString() {
		return "SubmissionItem [id=" + id + ", submissionId=" + submission.getId() + ", foodId=" + food.getId() + ", times=" + times
				+ "]";
	}



	
	
	
	

}