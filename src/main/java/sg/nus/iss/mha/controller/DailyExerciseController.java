package sg.nus.iss.mha.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.DailyExercise;
import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.repository.DailyExerciseRepository;
import sg.nus.iss.mha.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/daily-exercise")
public class DailyExerciseController {

    @Autowired
    private DailyExerciseRepository exRepo;
    @Autowired
    private UserRepository uRepo;
    
    
    //Get: http://localhost:8080/api/daily-exercise/calories-burnt-today/1
    @GetMapping("/calories-burnt-today/{userId}")
    public ResponseEntity<String> getCaloriesBurntToday(@PathVariable Integer userId) {
        try {
            LocalDate today = LocalDate.now();

            Integer caloriesBurntToday = exRepo.sumCaloriesBurntByUserIdAndExerciseDate(userId, today);

            return ResponseEntity.ok(String.valueOf(caloriesBurntToday != null ? caloriesBurntToday : 0));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("0");
        }
    }
    
   
    /*
    post: http://localhost:8080/api/daily-exercise/update-calories
    {
	  "userId": 1,
	  "exerciseDate": "2024-01-01",
	  "caloriesBurnt": 500
	}
     */
        
    // Endpoint to update calories for a user on a specific date
    @PostMapping("/update-calories")
    public ResponseEntity<String> updateCalories(@RequestBody Map<String, Object> requestBody) {
        Integer userId = (Integer) requestBody.get("userId");
        LocalDate exerciseDate = LocalDate.parse((String) requestBody.get("exerciseDate"));
        Integer caloriesBurnt = (Integer) requestBody.get("caloriesBurnt");
        try {
            // Check if there is an existing DailyExercise record for the user on the specified date
            Optional<DailyExercise> existingExercise = exRepo.findByUser_IdAndExerciseDate(userId, exerciseDate);

            if (existingExercise.isPresent()) {
                // If a record exists, update the calories
                DailyExercise exerciseToUpdate = existingExercise.get();
                exerciseToUpdate.setCaloriesBurnt(exerciseToUpdate.getCaloriesBurnt() + caloriesBurnt);
                exRepo.save(exerciseToUpdate);
            } else {
                // If no record exists, fetch the user and create a new one
                Optional<User> userOptional = uRepo.findById(userId);

                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    DailyExercise newExercise = new DailyExercise(exerciseDate, user, caloriesBurnt);
                    exRepo.save(newExercise);
                    return ResponseEntity.ok("New DailyExercise record created successfully");
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with the provided ID");
                }
            }

            return ResponseEntity.ok("Calories updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating calories");
        }
    }
    

    //Get: http://localhost:8080/api/daily-exercise/last-7-days/{userId}
    @GetMapping("/last-7-days/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getLast7DaysCalories(@PathVariable Integer userId) {
        try {
            LocalDate endDate = LocalDate.now();
            LocalDate startDate = endDate.minusDays(6);

            List<DailyExercise> last7DaysExercises = exRepo.findByUser_IdAndExerciseDateBetweenOrderByExerciseDateDesc(
                    userId, startDate, endDate);

            List<Map<String, Object>> responseList = generateResponseList(startDate, endDate, last7DaysExercises);

            return ResponseEntity.ok(responseList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    private List<Map<String, Object>> generateResponseList(LocalDate startDate, LocalDate endDate,
                                                           List<DailyExercise> exerciseList) {
        List<Map<String, Object>> responseList = new ArrayList<>();

        for (LocalDate currentDate = endDate; !currentDate.isBefore(startDate); currentDate = currentDate.minusDays(1)) {
            LocalDate currentDay = currentDate;  // Create a copy of currentDate
            Optional<DailyExercise> exerciseOptional = exerciseList.stream()
                    .filter(exercise -> exercise.getExerciseDate().equals(currentDay))
                    .findFirst();

            if (exerciseOptional.isPresent()) {
                responseList.add(mapToResponse(exerciseOptional.get()));
            } else {
                responseList.add(null); // Add null for days without data
            }
        }

        Collections.reverse(responseList); // Reverse the list to have it in ascending order

        return responseList;
    }

    private Map<String, Object> mapToResponse(DailyExercise exercise) {
        Map<String, Object> response = new HashMap<>();
        response.put("exerciseDate", exercise.getExerciseDate());
        response.put("caloriesBurnt", exercise.getCaloriesBurnt());
        return response;
    }
}