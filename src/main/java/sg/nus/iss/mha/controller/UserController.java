package sg.nus.iss.mha.controller;

import java.util.List;
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

import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.service.UserService;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
  @Autowired
  private UserService uService;
  
  @GetMapping("/users")
  public List<User> getAllUsers() {
    return uService.findAllUsers();
  }
  
  @GetMapping("/get/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Integer id) {
    Optional<User> optUser = uService.findUser(id);
    
    if (optUser.isPresent()) {
      User user = optUser.get();
      return new ResponseEntity<User>(user, HttpStatus.OK);
    } else {
      return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
  }
  
  
  /*
  {
  "name": "admin iss",
  "password": "0adminpassword",
  "birthDate": "2001-09-18",
  "gender" : "male",
  "emailAddress": "admin@email.com",
  "targetCalories": 3000
  }
   */

  @PostMapping("/create")
  public ResponseEntity<User> createUser(@RequestBody User inUser) {
    try {
    	User retUser = uService.createUser(inUser);      
      
      return new ResponseEntity<User>(retUser, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  /*
  @GetMapping("/getcalories/{id}")
  public ResponseEntity<String> getUserCaloriesById(@PathVariable Integer id) {
      Optional<User> optUser = uService.findUser(id);

      if (optUser.isPresent()) {
          User user = optUser.get();
          String targetCaloriesString = String.valueOf(user.getTargetCalories());
          return new ResponseEntity<>(targetCaloriesString, HttpStatus.OK);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }
  */
  
  @PostMapping("/updatecalories/{id}")
  public ResponseEntity<User> updateCalories(@PathVariable("id") Integer id, 
		  @RequestBody User inUser) {
    System.out.println("Received POST request with ID: " + id);
    System.out.println("Received Payload: " + inUser.toString());

    Optional<User> optUser = uService.findUser(id);
    
    if (optUser.isPresent()) {
      // Update the managed role obj
      User user = optUser.get();
      
      user.setTargetCalories(inUser.getTargetCalories());
      
      User updatedUser = uService.updateUser(user);
      
      return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/update/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") Integer id, 
		  @RequestBody User inUser) {
    Optional<User> optUser = uService.findUser(id);
    
    if (optUser.isPresent()) {
      // Update the managed role obj
      User user = optUser.get();
      
      user.setName(inUser.getName());
      user.setPassword(inUser.getPassword());
      user.setBirthDate(inUser.getBirthDate());
      user.setEmailAddress(inUser.getEmailAddress());
      
      User updatedUser = uService.updateUser(user);
      
      return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
  
  @PostMapping("/delete/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Integer id) {
    try {
    	uService.deleteUser(id);
      return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<HttpStatus>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
}

