package sg.nus.iss.mha.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.mha.model.Admin;
import sg.nus.iss.mha.service.AdminService;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/admin/login")
    public ResponseEntity<String> login(@RequestBody @Valid Admin admin, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>("Invalid input data", HttpStatus.BAD_REQUEST);
        }

        Admin authenticatedAdmin = adminService.authenticate(admin.getId(), admin.getPassword());
        if (authenticatedAdmin == null) {
            return new ResponseEntity<>("Incorrect Id/password", HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("Login successful", HttpStatus.OK);
    }

    }
