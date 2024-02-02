package sg.nus.iss.mha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.nus.iss.mha.model.User;
import sg.nus.iss.mha.service.UserService;

@RestController
@RequestMapping("/api")
public class CommonController {

    @Autowired
    private UserService uService;

    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpSession session) {
        session.invalidate();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody @Valid User user,
                  BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
        }
        User u = uService.authenticate(user.getName(), user.getPassword());
        if (u == null) {
            return new ResponseEntity<>("Incorrect username/password", HttpStatus.UNAUTHORIZED);
        }
        
        String userId = String.valueOf(u.getUserId()); 
        
        return new ResponseEntity<>(userId, HttpStatus.OK);
    }
    
    /*
    @GetMapping("/login/getuserid")
    public ResponseEntity<Integer> getUserIdByNameAndPassword(@RequestBody @Valid 
            User inUser, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        List<User> users = uService.getAllUsers();

        for (User storedUser : users) {
            if (storedUser.getName().equals(inUser.getName()) && 
            		storedUser.getPassword().equals(inUser.getPassword()) &&
            		uService.booleanAuthenticate(inUser.getName(), inUser.getPassword())) { 
                return new ResponseEntity<>(storedUser.getUserId(), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    */
}


/*
// Login successful, generate a token
String authToken = tokenService.generateToken(u);

// Store the token in the session
session.setAttribute("authToken", authToken);
*/