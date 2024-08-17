package com.ponsun.san.adminconfiguration.adminconfigmoduledet.services;



import com.ponsun.san.adminconfiguration.adminconfigmoduledet.data.AdminConfigModuleDetDataValidator;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDet;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDetRepository;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.domain.AdminConfigModuleDetRepositoryWrapper;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.CreateAdminConfigModuleDetRequest;
import com.ponsun.san.adminconfiguration.adminconfigmoduledet.request.UpdateAdminConfigModuleDetRequest;
import com.ponsun.san.common.entity.Status;
import com.ponsun.san.infrastructure.exceptions.EQAS_SAN_ApplicationException;
import com.ponsun.san.infrastructure.utils.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminConfigModuleDetWritePlatformServiceImpl implements AdminConfigModuleDetWritePlatformService  {

    private final AdminConfigModuleDetRepository adminconfigmoduledetRepository;
    private final AdminConfigModuleDetRepositoryWrapper adminConfigModuleDetRepositoryWrapper;
    private final AdminConfigModuleDetDataValidator moduleDetDataValidator;
    @Override
    @Transactional
    public Response createAdminConfigModuleDet(CreateAdminConfigModuleDetRequest createAdminConfigModuleDetRequest) {
        try {
            this.moduleDetDataValidator.validateSaveModuleDet(createAdminConfigModuleDetRequest);
            final AdminConfigModuleDet adminconfigmoduledet = AdminConfigModuleDet.create(createAdminConfigModuleDetRequest);//entity
            this.adminconfigmoduledetRepository.saveAndFlush(adminconfigmoduledet);
            return Response.of(Long.valueOf(adminconfigmoduledet.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
    @Override
    @Transactional
    public Response updateAdminConfigModuleDet(Integer id, UpdateAdminConfigModuleDetRequest updateAdminConfigModuleDetRequest) {
        try {
            this.moduleDetDataValidator.validateUpdateModuleDet(updateAdminConfigModuleDetRequest);
            final AdminConfigModuleDet moduleDet = this.adminConfigModuleDetRepositoryWrapper.findOneWithNotFoundDetection(id);
            moduleDet.update(updateAdminConfigModuleDetRequest);
            this.adminconfigmoduledetRepository.saveAndFlush(moduleDet);
            return Response.of(Long.valueOf(moduleDet.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

//    @Override
//    @Transactional
//    public Response deactive(Integer sanId, Integer euid){
//        try{
//            List<AdminConfigModuleDet> adminConfigModuleDetList = this.adminConfigModuleDetRepositoryWrapper.findOnesanIdWithNotFoundDetection(sanId);
//            Response response = null;
//            for (AdminConfigModuleDet adminConfigModuleDet : adminConfigModuleDetList) {
//                adminConfigModuleDet.setEuid(euid);
//                adminConfigModuleDet.setStatus(Status.ACTIVE);
//                adminConfigModuleDet.setUpdatedAt(LocalDateTime.now());
//                response = Response.of(adminConfigModuleDet.getId());
//            }
//            return response;
//        }catch (DataIntegrityViolationException e){
//            throw new EQAS_SAN_AppicationException(e.getMessage());
//        }
//    }

@Override
@Transactional
public Response blockAdminConfigModuleDet(Integer id) {
    try {
        final AdminConfigModuleDet adminConfigModuleDet = this.adminConfigModuleDetRepositoryWrapper.findOneWithNotFoundDetection(id);
        adminConfigModuleDet.setValid(false);
        adminConfigModuleDet.setStatus(Status.DELETE);
        adminConfigModuleDet.setUpdatedAt(LocalDateTime.now());
        this.adminconfigmoduledetRepository.saveAndFlush(adminConfigModuleDet);
        return Response.of(Long.valueOf(id));
    } catch (DataIntegrityViolationException e) {
        throw new EQAS_SAN_ApplicationException(e.getMessage());
    }
}

    @Override
    @Transactional
    public Response unblockAdminConfigModuleDet(Integer id) {
        try {
            final AdminConfigModuleDet adminConfigModuleDet = this.adminConfigModuleDetRepositoryWrapper.findOneWithNotFoundDetection(id);
            adminConfigModuleDet.setValid(true);
            adminConfigModuleDet.setStatus(Status.ACTIVE);
            adminConfigModuleDet.setUpdatedAt(LocalDateTime.now());
            this.adminconfigmoduledetRepository.saveAndFlush(adminConfigModuleDet);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}

