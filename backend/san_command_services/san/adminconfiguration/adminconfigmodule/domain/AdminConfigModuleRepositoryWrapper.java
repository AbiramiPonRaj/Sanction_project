package com.ponsun.san.adminconfiguration.adminconfigmodule.domain;

import com.ponsun.san.adminconfiguration.adminconfigmodule.request.AbstractAdminConfigModuleBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminConfigModuleRepositoryWrapper extends AbstractAdminConfigModuleBaseRequest {
    private final AdminConfigModuleRepository adminConfigModuleRepository;

    @Transactional
    public AdminConfigModule findOneWithNotFoundDetection (final Integer id) {
        return this.adminConfigModuleRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AdminConfigModule Not found " + id));
    }
//    @Transactional
//    public AdminConfigModule findSANWithNotFoundDetection (final Integer id) {
//        return this.adminConfigModuleRepository.findBysanId(id).orElseThrow(() -> new EntityNotFoundException("AdminConfigModule Not found " + id));
//    }
    @Override
    public String toString(){ return super.toString();}
}

