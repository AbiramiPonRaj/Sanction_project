package com.ponsun.san.master.sanctionsProgram.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SanctionsProgramRepository extends JpaRepository<SanctionsProgram,Integer> {
    Optional<SanctionsProgram> findById(Integer id);
}
