package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.nus.iss.mha.model.User;


public interface UserRepository extends JpaRepository <User, Integer>{
	  @Query("SELECT u FROM User u WHERE u.name=:username AND u.password=:pwd")
	  User findUserByNamePwd(@Param("username")String username, @Param("pwd")String pwd);
	  
 
}

