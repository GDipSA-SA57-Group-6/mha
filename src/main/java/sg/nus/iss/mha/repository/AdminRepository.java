package sg.nus.iss.mha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sg.nus.iss.mha.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
