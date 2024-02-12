package sg.nus.iss.mha.model;

import java.io.Serializable;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name = "diabetes_data")
public class DiabetesData implements Serializable{

    @EmbeddedId
    private DiabetesDataPK id;
    
    private Float highBP;
    private Float bmi;
    private Float heartDisease;
    private Float genHlth;
    private Float physHlth;
    private Float diffWalk;
    private Float income;
    private Integer predictionClass;
    private Double predictionProbability;

    public Integer getPredictionClass() {
		return predictionClass;
	}

	public void setPredictionClass(Integer predictionClass) {
		this.predictionClass = predictionClass;
	}

	public DiabetesDataPK getId() {
		return id;
	}

	public void setId(DiabetesDataPK id) {
		this.id = id;
	}

	public Float getHighBP() {
		return highBP;
	}

	public void setHighBP(Float highBP) {
		this.highBP = highBP;
	}

	public Float getBmi() {
		return bmi;
	}

	public void setBmi(Float bmi) {
		this.bmi = bmi;
	}

	public Float getHeartDisease() {
		return heartDisease;
	}

	public void setHeartDisease(Float heartDisease) {
		this.heartDisease = heartDisease;
	}

	public Float getGenHlth() {
		return genHlth;
	}

	public void setGenHlth(Float genHlth) {
		this.genHlth = genHlth;
	}

	public Float getPhysHlth() {
		return physHlth;
	}

	public void setPhysHlth(Float physHlth) {
		this.physHlth = physHlth;
	}

	public Float getDiffWalk() {
		return diffWalk;
	}

	public void setDiffWalk(Float diffWalk) {
		this.diffWalk = diffWalk;
	}

	public Float getIncome() {
		return income;
	}

	public void setIncome(Float income) {
		this.income = income;
	}
	

	public Double getPredictionProbability() {
		return predictionProbability;
	}

	public void setPredictionProbability(Double predictionProbability) {
		this.predictionProbability = predictionProbability;
	}

	public DiabetesData() {
    }
}
