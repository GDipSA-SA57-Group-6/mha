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
	private int cab;
	private String userId;
	private int type;

	@OneToMany(mappedBy = "food")
	private List<SubmissionItem> submissionItems;

	public Food() {}
	
	public Food(String name, String quantity_description, int cal, int protein, int fat, int cab, String userId, int type) {
		this.name = name;
		this.quantity_description = quantity_description;
		this.cal = cal;
		this.protein = protein;
		this.fat = fat;
		this.cab = cab;
		this.userId = userId;
		this.type = type;
		
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

	public int getCab() {
		return cab;
	}

	public void setCab(int cab) {
		this.cab = cab;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Food [id=" + id + ", name=" + name + ", quantity_description=" + quantity_description + ", protein="
				+ protein + ", cal=" + cal + ", fat=" + fat + ", cab=" + cab + ", userId=" + userId + ", type=" + type
				+ "]";
	}
	
}
