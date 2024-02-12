package sg.nus.iss.mha.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "heart_disease_data")
public class HeartDiseaseData implements Serializable {
    
    @EmbeddedId
    private HeartDiseaseDataPK id;
    
    private Integer cp;
    private Float trestbps;
    private Float thalach;
    private Integer exang;
    private Integer predictionClass;
    private Double predictionProbability; 

    

    public HeartDiseaseData() {
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
    public HeartDiseaseDataPK getId() {
        return id;
    }

    public void setId(HeartDiseaseDataPK id) {
        this.id = id;
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
