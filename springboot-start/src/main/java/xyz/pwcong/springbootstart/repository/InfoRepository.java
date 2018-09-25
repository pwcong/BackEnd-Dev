package xyz.pwcong.springbootstart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.pwcong.springbootstart.model.Info;

public interface InfoRepository extends JpaRepository<Info, Long> {
}

