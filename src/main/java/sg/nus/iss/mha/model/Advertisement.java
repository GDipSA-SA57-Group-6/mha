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
@Table(name = "advertisement")
public class Advertisement {

    @Id
    @Column(name="adv_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer advId;

    @Column(name = "adv_url", length = 65535)
    @NotNull
    private String advurl;

    @Column(name = "description")
    private String description;

    @Column(name = "type")
    @NotNull
    private Integer type;

    public Advertisement(String advUrl, Integer type){
        this.advurl = advUrl;
        this.type = type;
    }
    
}
