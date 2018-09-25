package xyz.pwcong.springbootstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.springbootstart.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
