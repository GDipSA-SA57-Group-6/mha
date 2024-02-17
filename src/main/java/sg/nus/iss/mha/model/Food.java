package sg.nus.iss.mha.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import sg.nus.iss.mha.model.SubmissionItem;

@Entity
public class Food {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String quantity_description;
	private int protein;
	private int cal; 
	private int fat;
	private int carb;
	private String userId;

	@OneToMany(mappedBy = "food")
	private List<SubmissionItem> submissionItems;

	public Food() {}
	
	public Food(String name, String quantity_description, int cal, int protein, int fat, int carb, String userId) {
		this.name = name;
		this.quantity_description = quantity_description;
		this.cal = cal;
		this.protein = protein;
		this.fat = fat;
		this.carb = carb;
		this.userId = userId;
		//this.type = type;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getQuantity_description() {
		return quantity_description;
	}

	public void setQuantity_description(String quantity_description) {
		this.quantity_description = quantity_description;
	}

	public int getProtein() {
		return protein;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public int getCal() {
		return cal;
	}

	public void setCal(int cal) {
		this.cal = cal;
	}

	public int getFat() {
		return fat;
	}

	public void setFat(int fat) {
		this.fat = fat;
	}

	public int getCarb() {
		return carb;
	}

	public void setCarb(int carb) {
		this.carb = carb;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", quantity_description=" + quantity_description + ", protein="
				+ protein + ", cal=" + cal + ", fat=" + fat + ", carb=" + carb + ", userId=" + userId + "]";
	}
	
}

