package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.nus.iss.mha.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer>{}
