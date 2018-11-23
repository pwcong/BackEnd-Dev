package xyz.pwcong.ssoauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.ssoauthserver.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String username);
}
