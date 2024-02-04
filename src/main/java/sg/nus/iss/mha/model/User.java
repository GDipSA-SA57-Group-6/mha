package sg.nus.iss.mha.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "user")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "userId")
public class User implements Serializable {
  public static final String DATE_FORMAT = "yyyy-MM-dd";
  private static final long serialVersionUID = 6529685098267757680L;
  @Id
  @Column(name="user_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;
  @Size(min=4,max=20, message = "User Name must be 4-20 characters")
  private String name;
  @Size(min=4,max=20, message = "Password must be 4-20 characters")
  @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).*$", message = 
		  "Password must contain at least one alphabet and one number")
  private String password;
  
  @Column(name="birth_date")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @NonNull
  private LocalDate birthDate;
  
  @NonNull
  private String gender;
  
  @Column(name="email_address")
  @Email
  @NonNull
  private String emailAddress;
  
  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
  private List<DailyExercise> dailyExercises;
  
  public User(String name, String password, LocalDate birthDate, String gender, 
		  String emailAddress) {
	    this.name = name;
	    this.password = password;
	    this.birthDate = birthDate;
	    this.gender = gender;
	    this.emailAddress = emailAddress;
	    setTargetCaloriesBasedOnGender();
  }
  
  private void setTargetCaloriesBasedOnGender() {
      this.targetCalories = "male".equalsIgnoreCase(gender) ? 2500 : 2000;	
  }

  @Column(name="target_calories")
  private int targetCalories;
  
}