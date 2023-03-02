package org.example.access.check.repository;


import org.example.access.check.model.AccessCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessCheckRepository extends JpaRepository<AccessCheckHistory, Integer> {
}
