package xyz.pwcong.springbootstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.springbootstart.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
