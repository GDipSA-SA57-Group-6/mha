package sg.nus.iss.mha.model;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "daily_exercise")
public class DailyExercise implements Serializable {
	
	private static final long serialVersionUID = 6529685098267757680L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ex_id")
    private Integer id;
	
    @Column(name="exercise_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NonNull
    private LocalDate exerciseDate;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @Column(name="calories_burnt")
    private Integer caloriesBurnt;
    
    public DailyExercise(LocalDate exerciseDate, User user,Integer caloriesBurnt) {
  	    this.exerciseDate = exerciseDate;
  	    this.user = user;
  	    this.caloriesBurnt = caloriesBurnt;
    }
}
