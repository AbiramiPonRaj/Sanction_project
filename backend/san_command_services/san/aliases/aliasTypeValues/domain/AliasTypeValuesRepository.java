package com.ponsun.san.aliases.aliasTypeValues.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AliasTypeValuesRepository extends JpaRepository<AliasTypeValues,Integer> {
    Optional<AliasTypeValues> findById(Integer id);
}
