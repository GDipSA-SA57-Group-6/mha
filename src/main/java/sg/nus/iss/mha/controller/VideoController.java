package sg.nus.iss.mha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.mha.model.Video;
import sg.nus.iss.mha.service.VideoService;
import sg.nus.iss.mha.service.UserService;
import sg.nus.iss.mha.service.DiabetesDataService;
import sg.nus.iss.mha.service.HeartDiseaseDataService;
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


    @Autowired
    public VideoController(VideoService videoService, UserService uService) {
        this.videoService = videoService;
        this.uService = uService;
    }

    @Autowired
    private DiabetesDataService diabetesDataService;

    @Autowired
    private HeartDiseaseDataService heartDiseaseDataService;

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
        if (optUser.isPresent()) {
            User user = optUser.get();
            int userType = user.getTargetCalories();
            Integer heartDiseaseClass = heartDiseaseDataService.getLatestPredictionClassByUserId(userId);
            Integer diabetesClass = diabetesDataService.getLatestPredictionClassByUserId(userId);

            if (heartDiseaseClass == 0 && diabetesClass == 0) {
                // 没有心脏病和糖尿病风险
                if (userType == 1900 || userType == 1400) {
                    return ResponseEntity.ok(1);
                } else if (userType == 2500 || userType == 2000) {
                    return ResponseEntity.ok(2);
                } else if (userType == 2800 || userType == 2300) {
                    return ResponseEntity.ok(3);
                }
            } else {
                // 有心脏病或糖尿病风险
                if (userType == 1900 || userType == 1400) {
                    return ResponseEntity.ok(4);
                } else if (userType == 2500 || userType == 2000) {
                    return ResponseEntity.ok(5);
                } else if (userType == 2800 || userType == 2300) {
                    return ResponseEntity.ok(6);
                }
            }
        }
        return ResponseEntity.notFound().build();
    }

    // 说明用于推荐的卡路里和年龄的标准
    // 男性 lw:1900 mh:2500 gw:2800
    // 女性 lw:1400 mh:2000 gw:2300

    @GetMapping("/videos/{type}")
    public ResponseEntity<List<Video>> getVideosByType(@PathVariable Integer type) {
        // 调用VideoService来获取视频列表
        List<Video> videos = videoService.getVideosByType(type);
        return new ResponseEntity<>(videos, HttpStatus.OK);
    }
}