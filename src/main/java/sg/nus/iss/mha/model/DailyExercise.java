package sg.nus.iss.mha.model;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class DailyExercise{
	
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
