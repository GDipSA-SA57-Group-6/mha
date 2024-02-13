package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import sg.nus.iss.mha.model.Food;
import sg.nus.iss.mha.repository.FoodRepository;
import sg.nus.iss.mha.service.FoodService;

@Service
@Transactional(readOnly = true)
public class FoodServiceImpl implements FoodService{
	@Resource
    private FoodRepository foodReposiotory;
	
	@Override
	public List<Food> findAllFood() {
		// TODO Auto-generated method stub
		return foodReposiotory.findAll();
		
	}

	@Transactional(readOnly = false)
	@Override
	public Food createFood(Food food) {
		// TODO Auto-generated method stub
		return foodReposiotory.save(food);
		}

	@Transactional(readOnly = false)
	@Override
	public void deleteFood(int id) {
		// TODO Auto-generated method stub
		foodReposiotory.deleteById(id);
	}

	
	@Transactional(readOnly = true)
	@Override
	public Optional<Food> findFood(int id) {
		// TODO Auto-generated method stub
		return foodReposiotory.findById(id);
	}




}
