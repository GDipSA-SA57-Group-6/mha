package sg.nus.iss.mha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.mha.repository.VideoRepository;
import sg.nus.iss.mha.model.Video;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl( VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }
}
