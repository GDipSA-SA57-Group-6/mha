package sg.nus.iss.mha.service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sg.nus.iss.mha.model.HeartDiseaseData;
import sg.nus.iss.mha.model.HeartDiseaseDataDto;
import sg.nus.iss.mha.model.HeartDiseaseDataPK;
import sg.nus.iss.mha.repository.HeartDiseaseDataRepository;

@Service
public class HeartDiseaseDataService {

    @Autowired
    private HeartDiseaseDataRepository heartDiseaseDataRepository;

    public void saveHeartDiseaseData(HeartDiseaseDataDto dto) {
        HeartDiseaseData heartDiseaseData = convertDtoToEntity(dto);
        heartDiseaseDataRepository.save(heartDiseaseData);
    }

    private HeartDiseaseData convertDtoToEntity(HeartDiseaseDataDto dto) {
        HeartDiseaseData entity = new HeartDiseaseData();
        HeartDiseaseDataPK pk = new HeartDiseaseDataPK();
        
        // 设置复合主键
        pk.setUserId(dto.getUserId());
        pk.setDate(LocalDate.parse(dto.getDate(), DateTimeFormatter.ISO_LOCAL_DATE)); // 假设日期格式为ISO标准
        
        // 设置实体的主键
        entity.setId(pk);
        
        entity.setPredictionClass(dto.getPredictionClass());
        entity.setCp(dto.getCp());
        entity.setTrestbps(dto.getTrestbps());
        entity.setThalach(dto.getThalach());
        entity.setExang(dto.getExang());
        entity.setPredictionProbability(dto.getPredictionProbability());

        // 根据需要添加更多字段的设置

        return entity;
    }
//    public List<HeartDiseaseData> getPredictionsForLastSevenDays(Integer userId) {
//        LocalDate today = LocalDate.now();
//        List<HeartDiseaseData> predictions = new ArrayList<>();
//        // 遍历过去7天
//        for (int i = 0; i < 7; i++) {
//            LocalDate date = today.minusDays(i);
//            // 使用修改后的方法签名直接查询
//            HeartDiseaseData prediction = heartDiseaseDataRepository.findById_UserIdAndId_Date(userId, date)
//                .orElse(new HeartDiseaseData()); // 如果那天没有数据，则可能创建一个默认值或跳过
//            predictions.add(prediction);
//        }
//        return predictions;
//    }
//    
    public List<HeartDiseaseData> getPredictionsForLastSevenDays(Integer userId) {
        LocalDate today = LocalDate.now();
        List<HeartDiseaseData> predictions = new ArrayList<>();
        // 遍历过去7天
        for (int i = 0; i < 7; i++) {
            LocalDate date = today.minusDays(i);
            HeartDiseaseDataPK pk = new HeartDiseaseDataPK(userId, date);
            // 尝试根据复合主键获取预测数据
            Optional<HeartDiseaseData> optionalPrediction = heartDiseaseDataRepository.findById(pk);
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
        return heartDiseaseDataRepository.findLatestByUserId(userId, topOne)
                .stream()
                .findFirst()
                .map(HeartDiseaseData::getPredictionClass) // 直接映射到predictionClass
                .orElse(null); // 如果没有找到记录，返回null或其他默认值
    }

}
