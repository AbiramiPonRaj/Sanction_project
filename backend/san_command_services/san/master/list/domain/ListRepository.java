package com.ponsun.san.master.list.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ListRepository extends JpaRepository<Lists,Integer> {
    Optional<Lists> findById(Integer id);
}
