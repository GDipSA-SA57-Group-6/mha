package sg.nus.iss.mha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.mha.model.Video;
import sg.nus.iss.mha.service.VideoService;
import sg.nus.iss.mha.service.UserService;
import sg.nus.iss.mha.model.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/video")
@CrossOrigin(origins = "http://localhost:3000")
public class VideoController {

    private final VideoService videoService;
    private final UserService uService;
    //应该还有一个  心脏病和糖尿病的service

    @Autowired
    public VideoController(VideoService videoService, UserService uService) {
        this.videoService = videoService;
        this.uService = uService;
    }

    @GetMapping
    public List<Video> getAllVideos() {
        return videoService.getAllVideos();
    }

    @PostMapping
    public ResponseEntity<String> saveVideo(@RequestBody Video video) {
        videoService.saveVideo(video);
        return ResponseEntity.ok("New Video record created successfully");
    }

    @GetMapping("/path/{userId}")
    public ResponseEntity<Integer> recommendVideo(@PathVariable Integer userId) {
        int type = -1;
        Optional<User> optUser = uService.findUser(userId);
        if(optUser.isPresent()){
            User user = optUser.get();
            int userType = user.getTargetCalories();
            LocalDate currentDate = LocalDate.now();
            LocalDate birthDate = user.getBirthDate();
            Period period = Period.between(birthDate, currentDate);
            int age = period.getYears();
            if (userType == 1900 || userType == 2500 || userType == 2800) {
                if (age < 45) {
                    type = 1;  // 男性
                } else type = 2;
            }
            if (userType == 1400 || userType == 2000 || userType == 2300) {
                if (age < 45) {
                    type = 3;  // 女性
                } else type = 4;
            }
        }
        return new ResponseEntity<>(type, HttpStatus.OK);
    }

    // 说明用于推荐的卡路里和年龄的标准
    // 男性 lw:1900 mh:2500 gw:2800
    // 女性 lw:1400 mh:2000 gw:2300


}
