package sg.nus.iss.mha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.mha.model.Advertisement;
import sg.nus.iss.mha.service.AdvertisementService;
import sg.nus.iss.mha.service.UserService;
import sg.nus.iss.mha.service.UserService;
import sg.nus.iss.mha.model.User;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/advertisements")
@CrossOrigin(origins = "http://localhost:3000")
public class AdvertisementController {

    private final AdvertisementService advertisementService;
    private final UserService uService;

//    @Autowired
//    public AdvertisementController(AdvertisementService advertisementService) {
//        this.advertisementService = advertisementService;
//    }

    @Autowired // 添加了UserService的注入
    public AdvertisementController(AdvertisementService advertisementService, UserService uService) {
        this.advertisementService = advertisementService;
        this.uService = uService; // 使用构造函数注入UserService
    }

    @GetMapping
    public List<Advertisement> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    @PostMapping
    public ResponseEntity<String> saveAdvertisement(@RequestBody Advertisement advertisement) {
        advertisementService.saveAdvertisement(advertisement);
        return ResponseEntity.ok("New Advertisement record created successfully");
    }


    @GetMapping("/path/{userId}")
    public ResponseEntity<List<Advertisement>> recommend_Advertidement(@PathVariable Integer userId) {
        //int type=-1;
    List<Advertisement> advList = new ArrayList<>();
    Optional<User> optUser = uService.findUser(userId);
        if(optUser.isPresent()){
    User user = optUser.get();
    int userType = user.getTargetCalories();
    LocalDate currentDate = LocalDate.now();
    LocalDate birthDate = user.getBirthDate();
    Period period = Period.between(birthDate, currentDate);
    int age = period.getYears();
    if (userType==1900||userType==2500||userType==2800) {
    if (age<45) {
    advList = advertisementService.getAdvertisementsByType(1); //male
    }
     else advList = advertisementService.getAdvertisementsByType(2);
    }
    if (userType==1400||userType==2000||userType==2300) {
    if (age<45) {
    advList = advertisementService.getAdvertisementsByType(3); //female
    }
    else advList = advertisementService.getAdvertisementsByType(4);
    }
    }
    return ResponseEntity.ok(advList);
    }
//    @GetMapping("/path/{userId}")
//    public ResponseEntity<Integer> recommendAdvertidement(@PathVariable Integer userId) {
//        int type=-1;
//        Optional<User> optUser = uService.findUser(userId);
//        if(optUser.isPresent()){
//            User user = optUser.get();
//            int userType = user.getTargetCalories();
//            LocalDate currentDate = LocalDate.now();
//            LocalDate birthDate = user.getBirthDate();
//            Period period = Period.between(birthDate, currentDate);
//            int age = period.getYears();
//            if (userType==1900||userType==2500||userType==2800) {
//                if (age<45) {
//                    type = 1;  //male
//                }
//                else type = 2;
//            }
//            if (userType==1400||userType==2000||userType==2300) {
//                if (age<45) {
//                    type = 3;  //female
//                }
//                else type = 4;
//            }
//        }
//        return new ResponseEntity<>(type, HttpStatus.OK);
//    }

    //male lw:1900 mh:2500 gw:2800
    //female lw:1400 mh:2000 gw:2300
    
}
