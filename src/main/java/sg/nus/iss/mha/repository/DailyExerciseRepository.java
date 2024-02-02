package sg.nus.iss.mha.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.mha.model.DailyExercise;

public interface DailyExerciseRepository extends JpaRepository<DailyExercise, Integer> {
    @Query("SELECT COALESCE(SUM(de.caloriesBurnt), 0) FROM DailyExercise de " +
            "WHERE de.user.id = :userId AND de.exerciseDate = :today")
     Integer sumCaloriesBurntByUserIdAndExerciseDate
     (@Param("userId") Integer userId, @Param("today") LocalDate today);
	
	/*
    @Query("SELECT de FROM DailyExercise de " +
            "WHERE de.user.id = :userId " +
            "AND de.exerciseDate BETWEEN :startDate AND :endDate " +
            "ORDER BY de.exerciseDate DESC")
     List<DailyExercise> findByUserIdAndDateBetweenOrderByDateDesc(
             @Param("userId") Integer userId,
             @Param("startDate") LocalDate startDate,
             @Param("endDate") LocalDate endDate
     );
    */
    @Query("SELECT de FROM DailyExercise de " +
            "WHERE de.user.id = :userId " +
            "AND de.exerciseDate = :exerciseDate")
     Optional<DailyExercise> findByUser_IdAndExerciseDate(@Param("userId")
     		Integer userId, @Param("exerciseDate") LocalDate exerciseDate);
 
  
    /*
    @Modifying
    @Query("UPDATE DailyExercise de SET de.caloriesBurnt = :caloriesBurnt WHERE de.user.id = :userId")
    void updateCaloriesBurntByUserId(@Param("userId") Integer userId, 
    		@Param("caloriesBurnt") Integer caloriesBurnt);
    */
    
    @Query("SELECT de FROM DailyExercise de " +
            "WHERE de.user.id = :userId " +
            "AND de.exerciseDate BETWEEN :startDate AND :endDate " +
            "ORDER BY de.exerciseDate DESC")
     List<DailyExercise> findByUser_IdAndExerciseDateBetweenOrderByExerciseDateDesc(
             @Param("userId") Integer userId,
             @Param("startDate") LocalDate startDate,
             @Param("endDate") LocalDate endDate);
    

    
	
}