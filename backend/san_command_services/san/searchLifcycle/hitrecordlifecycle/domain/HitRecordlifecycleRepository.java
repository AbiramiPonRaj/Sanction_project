package com.ponsun.san.searchLifcycle.hitrecordlifecycle.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface HitRecordlifecycleRepository extends JpaRepository<HitRecordLifecycle, Integer>{
    Optional<HitRecordLifecycle> findById(Integer id);
//    void updateValidBySearchIdAndCriminalId(Integer searchId, Integer criminalId,boolean valid);
//}
@Modifying
@Query("UPDATE HitRecordLifecycle h SET h.valid = :valid WHERE h.searchId = :searchId AND h.criminalId = :criminalId")
void updateValidBySearchIdAndCriminalId(Integer searchId, Integer criminalId, boolean valid);
}