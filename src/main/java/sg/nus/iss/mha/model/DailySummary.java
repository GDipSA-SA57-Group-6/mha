package sg.nus.iss.mha.model;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "daily_summary")
public class DailySummary {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "user_id")
	private int userId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
	private int cal_sum;
	private int protein_sum; 
	private int fat_sum;
	private int carb_sum;
	
	

	public DailySummary() {}
	
	public DailySummary(int userId,LocalDate date,int cal_sum, int protein_sum,int fat_sum, int carb_sum) {
		this.userId=userId;
		this.date=date;
		this.cal_sum = cal_sum;
		this.protein_sum=protein_sum;
		this.fat_sum = fat_sum;
		this.carb_sum =carb_sum;
		
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

	public int getCal_sum() {
		return cal_sum;
	}

	public void setCal_sum(int cal_sum) {
		this.cal_sum = cal_sum;
	}

	public int getProtein_sum() {
		return protein_sum;
	}

	public void setProtein_sum(int protein_sum) {
		this.protein_sum = protein_sum;
	}

	public int getFat_sum() {
		return fat_sum;
	}

	public void setFat_sum(int fat_sum) {
		this.fat_sum = fat_sum;
	}

	public int getCarb_sum() {
		return carb_sum;
	}

	public void setCarb_sum(int carb_sum) {
		this.carb_sum = carb_sum;
	}

	@Override
	public String toString() {
		return "DailySummary [id=" + id + ", userId=" + userId + ", date=" + date + ", cal_sum=" + cal_sum
				+ ", protein_sum=" + protein_sum + ", fat_sum=" + fat_sum + ", carb_sum=" + carb_sum + "]";
	}
	
	
	
	

}
