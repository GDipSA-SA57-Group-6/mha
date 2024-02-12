package sg.nus.iss.mha.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import jakarta.persistence.Embeddable;
@Embeddable
public class HeartDiseaseDataPK implements Serializable {
    private Integer userId;
    private LocalDate date;

    public HeartDiseaseDataPK() {
    }
    public HeartDiseaseDataPK(Integer userId, LocalDate date) {
        this.userId = userId;
        this.date = date;
    }

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HeartDiseaseDataPK other = (HeartDiseaseDataPK) obj;
		return Objects.equals(date, other.date) && Objects.equals(userId, other.userId);
	}

    
}
