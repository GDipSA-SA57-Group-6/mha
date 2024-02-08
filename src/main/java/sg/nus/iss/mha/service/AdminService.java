package sg.nus.iss.mha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.nus.iss.mha.model.Admin;
import sg.nus.iss.mha.repository.AdminRepository;

import java.util.Optional;

@Service
public class AdminService {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin authenticate(Long id, String password) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent() && optionalAdmin.get().getPassword().equals(password)) {
            return optionalAdmin.get();
        }
        return null;
    }
}
