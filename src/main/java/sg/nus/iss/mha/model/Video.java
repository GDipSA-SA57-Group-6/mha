package sg.nus.iss.mha.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Table(name = "video")
public class Video {

    @Id
    @Column(name = "vid_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vidId;

    @Column(name = "vid_url", columnDefinition = "TEXT")
    @NotNull
    private String vidUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "type")
    @NotNull
    private Integer type;

    public Video(String vidUrl, Integer type){
        this.vidUrl = vidUrl;
        this.type = type;
    }
}