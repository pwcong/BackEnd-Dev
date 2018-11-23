package xyz.pwcong.ssoauthserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.ssoauthserver.model.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {
}

