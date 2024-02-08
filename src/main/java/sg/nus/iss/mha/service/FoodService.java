package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.mha.model.Food;

public interface FoodService {
	   
	   Optional<Food> findFood(int id);
	   
	   Food createFood(Food food);
	   
	   void deleteFood(int id);

	   List<Food> findAllFood();


	   }