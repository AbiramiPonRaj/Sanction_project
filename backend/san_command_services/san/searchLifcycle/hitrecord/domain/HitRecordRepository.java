//package com.amlacademy.ponsun.searchLifcycle.hitdata.domain;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//public interface HitDataRepository extends JpaRepository<HitData, Integer> {
//    Optional<HitData> findById(Integer id);
//
//
////    @Modifying
////    @Query("SELECT id FROM crm_hitdata WHERE criminal_id=:criminalId AND search_id=:searchId")
//    Optional<HitData> findHitId(Integer criminalId, Integer searchId);
//
//}
package com.ponsun.san.searchLifcycle.hitrecord.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
public interface HitRecordRepository extends JpaRepository<HitRecord, Integer> {

    Optional<HitRecord> findById(Integer id);

    @Query("SELECT h FROM HitRecord h WHERE h.criminalId = :criminalId AND h.searchId = :searchId")
    Optional<HitRecord> findHitId(Integer criminalId, Integer searchId);

    @Modifying
    @Query("UPDATE HitRecord a SET a.valid = :valid WHERE a.id = :hitId")
    default void updateValidityById(boolean valid, Integer hitId) {

    }
}
