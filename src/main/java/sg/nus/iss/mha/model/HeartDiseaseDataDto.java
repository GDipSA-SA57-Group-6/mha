package sg.nus.iss.mha.model;

public class HeartDiseaseDataDto {
    private Integer userId;
    private String date; 
    private Integer cp;
    private Float trestbps;
    private Float thalach;
    private Integer exang;
    private Double predictionProbability;
    private Integer predictionClass;
    public HeartDiseaseDataDto() {
    	
    }
    public Double getPredictionProbability() {
        return predictionProbability;
    }

    public void setPredictionProbability(Double predictionProbability) {
        this.predictionProbability = predictionProbability;
    }
    public Integer getPredictionClass() {
        return predictionClass;
    }

    public void setPredictionClass(Integer predictionClass) {
        this.predictionClass = predictionClass;
    }
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getCp() {
		return cp;
	}
	public void setCp(Integer cp) {
		this.cp = cp;
	}
	public Float getTrestbps() {
		return trestbps;
	}
	public void setTrestbps(Float trestbps) {
		this.trestbps = trestbps;
	}
	public Float getThalach() {
		return thalach;
	}
	public void setThalach(Float thalach) {
		this.thalach = thalach;
	}
	public Integer getExang() {
		return exang;
	}
	public void setExang(Integer exang) {
		this.exang = exang;
	}

    
}

