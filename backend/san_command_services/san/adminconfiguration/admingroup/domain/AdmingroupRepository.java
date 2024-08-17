package com.ponsun.san.adminconfiguration.admingroup.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdmingroupRepository extends JpaRepository<Admingroup, Integer> {
    Optional<Admingroup> findById(Integer id);
}
