package sg.nus.iss.mha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.Food;
import sg.nus.iss.mha.service.FoodService;

@RestController
@RequestMapping("/api")
public class FoodController {
	@Autowired
	private FoodService foodService;
	
	@GetMapping("/food")
	public List<Food> getAllFood(){
		return foodService.findAllFood();
	}
	
	@PostMapping("/food")
	public ResponseEntity<Food> createFood(@RequestBody Food inFood){
		try {
			Food retFood = foodService.createFood(inFood);
		    return new ResponseEntity<Food>(retFood, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);				
		}
		
	}
}

