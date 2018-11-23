package xyz.pwcong.ssoauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.ssoauthserver.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
