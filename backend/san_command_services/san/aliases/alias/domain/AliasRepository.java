package com.ponsun.san.aliases.alias.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AliasRepository extends JpaRepository<Alias,Integer> {

    Optional<Alias> findById(Integer id);
}
