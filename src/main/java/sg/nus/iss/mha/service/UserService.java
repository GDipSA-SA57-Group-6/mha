package sg.nus.iss.mha.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.mha.model.User;

public interface UserService {
	List<User> findAllUsers();
	Optional<User> findUser(Integer id);
	User createUser(User user);
	User updateUser(User user);
	void deleteUser(Integer id);
	List<User> getAllUsers();
	
	  /**
	   * Return the respective User object if username and password are correct, null otherwise
	   * @param username
	   * @param pwd
	   * @return
	   */
	User authenticate(String username, String pwd);
	
}
