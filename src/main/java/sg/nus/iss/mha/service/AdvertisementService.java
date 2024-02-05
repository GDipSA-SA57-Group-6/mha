package sg.nus.iss.mha.service;

import sg.nus.iss.mha.model.Advertisement;
import java.util.List;

public interface AdvertisementService {
    List<Advertisement> getAllAdvertisements();
    Advertisement saveAdvertisement(Advertisement advertisement);
}
