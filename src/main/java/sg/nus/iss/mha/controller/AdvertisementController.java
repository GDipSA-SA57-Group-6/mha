package sg.nus.iss.mha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sg.nus.iss.mha.model.Advertisement;
import sg.nus.iss.mha.service.AdvertisementService;
import java.util.List;

@RestController
@RequestMapping("/advertisements")
public class AdvertisementController {

    private final AdvertisementService advertisementService;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping
    public List<Advertisement> getAllAdvertisements() {
        return advertisementService.getAllAdvertisements();
    }

    @PostMapping
    public Advertisement saveAdvertisement(@RequestBody Advertisement advertisement) {
        return advertisementService.saveAdvertisement(advertisement);
    }

    
}
