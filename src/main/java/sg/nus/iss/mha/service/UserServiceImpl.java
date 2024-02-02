package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository uRepo;
	    
	@Override
	public List<User> findAllUsers() {
		return uRepo.findAll();
	}
	
	@Override
	public Optional<User> findUser(Integer id) {
		return uRepo.findById(id);
	}
	
	@Transactional(readOnly = false)
	@Override
	public User createUser(User user) {
		return uRepo.save(user);
	}
	
	
	@Transactional(readOnly = false)
	@Override
	public User updateUser(User user) {
		return uRepo.save(user);
	}
	
	@Transactional(readOnly = false)
	@Override
	public void deleteUser(Integer id) {
		uRepo.deleteById(id);
	}

    @Transactional
    @Override
    public User authenticate(String username, String pwd) {
      return uRepo.findUserByNamePwd(username, pwd);
    }
    
    @Transactional
    @Override
    public List<User> getAllUsers() {
        return uRepo.findAll();
    }
        
    
}

/*
	@Transactional(readOnly = false)
	@Override
	public User createUser(User user) {
        // Save the user
        User savedUser = uRepo.save(user);
        
        // Calculate and set calories based on gender
        int caloriesValue = ("male".equalsIgnoreCase(user.getGender())) 
        		? 2500 : 2000;

        // Create Calories entity and associate it with the user
        TargetCalories calories = new TargetCalories();
        calories.setUserId(savedUser.getUserId());
        calories.setCalories(caloriesValue);
        calories.setUser(savedUser);
        
        // Save the TargetCalories entity
        tcRepo.save(calories);
        
        return savedUser;
	}
 */
