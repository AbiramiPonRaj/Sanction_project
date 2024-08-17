package com.ponsun.san.sanction.nameDetails.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NameDetailsRepository extends JpaRepository<NameDetails, Integer> {
    Optional<NameDetails> findById(Integer id);


}