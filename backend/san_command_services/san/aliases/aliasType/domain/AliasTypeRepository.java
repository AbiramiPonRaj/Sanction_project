package com.ponsun.san.aliases.aliasType.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AliasTypeRepository extends JpaRepository<AliasType,Integer> {
    Optional<AliasType> findById(Integer id);
}
