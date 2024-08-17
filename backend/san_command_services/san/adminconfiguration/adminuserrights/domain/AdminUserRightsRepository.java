package com.ponsun.san.adminconfiguration.adminuserrights.domain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminUserRightsRepository extends JpaRepository<AdminUserRights, Integer> {
    Optional<AdminUserRights> findById(Integer id);
}