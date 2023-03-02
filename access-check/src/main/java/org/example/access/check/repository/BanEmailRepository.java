package org.example.access.check.repository;

import org.example.access.check.model.BanEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanEmailRepository extends JpaRepository<BanEmail, Integer> {

    boolean existsBanEmailsByEmail(String email);
}
