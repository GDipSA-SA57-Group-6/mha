package sg.nus.iss.mha.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.model.HeartDiseaseDataDto;
import sg.nus.iss.mha.service.HeartDiseaseDataService;

@RestController
public class HeartDiseaseDataController {

    @Autowired
    private HeartDiseaseDataService heartDiseaseDataService;

    @PostMapping("/api/heartDiseaseData")
    public ResponseEntity<?> saveHeartDiseaseData(@RequestBody HeartDiseaseDataDto heartDiseaseDataDto) {
        heartDiseaseDataService.saveHeartDiseaseData(heartDiseaseDataDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/heartDiseasePredictionsLastSevenDays")
    public ResponseEntity<List<HeartDiseaseData>> getPredictionsForLastSevenDays(@RequestParam Integer userId) {
        List<HeartDiseaseData> predictions = heartDiseaseDataService.getPredictionsForLastSevenDays(userId);
        return ResponseEntity.ok(predictions);
    }
    @GetMapping("/api/latestHeartDiseasePredictionClass")
    public ResponseEntity<Integer> getLatestPredictionClass(@RequestParam Integer userId) {
        Integer predictionClass = heartDiseaseDataService.getLatestPredictionClassByUserId(userId);
        if (predictionClass != null) {
            return ResponseEntity.ok(predictionClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

