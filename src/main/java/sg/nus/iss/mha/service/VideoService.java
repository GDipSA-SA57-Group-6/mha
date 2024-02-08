package sg.nus.iss.mha.service;

import sg.nus.iss.mha.model.Video;
import java.util.List;
public interface VideoService {
    List<Video> getAllVideos();
    Video saveVideo(Video video);

    List<Video> getVideosByType(Integer type);
}
