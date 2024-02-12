package sg.nus.iss.mha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sg.nus.iss.mha.model.DiabetesData;
import sg.nus.iss.mha.model.DiabetesDataDto;
import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.service.DiabetesDataService;


@RestController
public class DiabetesDataController {
	@Autowired
    private DiabetesDataService diabetesDataService;

    @PostMapping("/api/diabetesData")
    public ResponseEntity<?> saveDiabetesData(@RequestBody DiabetesDataDto diabetesDataDto) {
        diabetesDataService.saveDiabetesData(diabetesDataDto);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/diabetesPredictionsLastSevenDays")
    public ResponseEntity<List<DiabetesData>> getPredictionsForLastSevenDays(@RequestParam Integer userId) {
        List<DiabetesData> predictions = diabetesDataService.getPredictionsForLastSevenDays(userId);
        return ResponseEntity.ok(predictions);
    }
    @GetMapping("/api/latestDiabetesPredictionClass")
    public ResponseEntity<Integer> getLatestPredictionClass(@RequestParam Integer userId) {
        Integer predictionClass = diabetesDataService.getLatestPredictionClassByUserId(userId);
        if (predictionClass != null) {
            return ResponseEntity.ok(predictionClass);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
