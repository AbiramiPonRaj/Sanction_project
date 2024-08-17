package com.ponsun.san.searchLifcycle.hitcase.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HitcaseRepository extends JpaRepository<Hitcase, Integer> {
    Optional<Hitcase> findById(Integer id);
}