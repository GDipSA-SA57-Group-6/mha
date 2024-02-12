package sg.nus.iss.mha.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sg.nus.iss.mha.model.DiabetesData;
import sg.nus.iss.mha.model.DiabetesDataDto;
import sg.nus.iss.mha.model.DiabetesDataPK;
import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.model.HeartDiseaseDataPK;
import sg.nus.iss.mha.repository.DiabetesDataRepository;




@Service
public class DiabetesDataService {

    @Autowired
    private DiabetesDataRepository diabetesDataRepository;

    public void saveDiabetesData(DiabetesDataDto dto) {
        DiabetesData diabetesData = convertDtoToEntity(dto);
        diabetesDataRepository.save(diabetesData);
    }

    private DiabetesData convertDtoToEntity(DiabetesDataDto dto) {
        DiabetesData entity = new DiabetesData();
        DiabetesDataPK pk = new DiabetesDataPK();
        
        // 设置复合主键
        pk.setUserId(dto.getUserId());
        pk.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)); // 假设日期格式为ISO标准
        
        // 设置实体的主键
        entity.setId(pk);
        
        entity.setPredictionClass(dto.getPredictionClass());
        entity.setBmi(dto.getBmi());
        entity.setGenHlth(dto.getGenHlth());
        entity.setDiffWalk(dto.getDiffWalk());
        entity.setHeartDisease(dto.getHeartDisease());
        entity.setHighBP(dto.getHighBP());
        entity.setIncome(dto.getIncome());
        entity.setPhysHlth(dto.getPhysHlth());
        entity.setPredictionProbability(dto.getPredictionProbability());
        return entity;
    }
    public List<DiabetesData> getPredictionsForLastSevenDays(Integer userId) {
        LocalDate today = LocalDate.now();
        List<DiabetesData> predictions = new ArrayList<>();
        // 遍历过去7天
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.minusDays(i);
            DiabetesDataPK pk = new DiabetesDataPK(userId, date);
            // 尝试根据复合主键获取预测数据
            Optional<DiabetesData> optionalPrediction = diabetesDataRepository.findById(pk);
            optionalPrediction.ifPresent(prediction -> {
                if (prediction.getId() != null) { // 检查 id 是否为 null
                    predictions.add(prediction);
                }
            });
        }
        return predictions;
    }
    public Integer getLatestPredictionClassByUserId(Integer userId) {
        PageRequest topOne = PageRequest.of(0, 1);
        return diabetesDataRepository.findLatestByUserId(userId, topOne)
                .stream()
                .findFirst()
                .map(DiabetesData::getPredictionClass) // 直接映射到predictionClass
                .orElse(null); // 如果没有找到记录，返回null或其他默认值
    }
}