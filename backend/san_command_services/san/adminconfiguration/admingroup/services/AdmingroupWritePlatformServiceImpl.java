//package com.amlacademy.ponsun.adminconfiguration.admingroup.services;
//
//
// import com.amlacademy.ponsun.adminconfiguration.admingroup.data.AdmingroupDataValidator;
// import com.amlacademy.ponsun.adminconfiguration.admingroup.domain.Admingroup;
//import com.amlacademy.ponsun.adminconfiguration.admingroup.domain.AdmingroupRepository;
// import com.amlacademy.ponsun.adminconfiguration.admingroup.domain.AdmingroupRepositoryWrapper;
// import com.amlacademy.ponsun.adminconfiguration.admingroup.request.CreateAdmingroupRequest;
// import com.amlacademy.ponsun.adminconfiguration.admingroup.request.UpdateAdmingroupRequest;
// import com.amlacademy.ponsun.common.entity.Status;
// import com.amlacademy.ponsun.infrastructure.exceptions.CrmAppicationException;
//import com.amlacademy.ponsun.infrastructure.utils.Response;
// import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.stereotype.Service;
//
// import java.time.LocalDateTime;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class AdmingroupWritePlatformServiceImpl implements AdmingroupWritePlatformService {
//    // Implement the
//    private final AdmingroupRepository admingroupRepository;
//    private final AdmingroupRepositoryWrapper admingroupRepositoryWrapper;
//    private final AdmingroupDataValidator admingroupDataValidator;
//    @Override
//    @Transactional
//    public Response createAdmingroup(CreateAdmingroupRequest createAdmingroupRequest) {
//        try {
//            this.admingroupDataValidator.validateSaveAdmingroup(createAdmingroupRequest);
//            final Admingroup admingroup = Admingroup.create(createAdmingroupRequest);//entity
//            this.admingroupRepository.saveAndFlush(admingroup);
//            return Response.of(Long.valueOf(admingroup.getId()));
//        } catch (DataIntegrityViolationException e) {
//            throw new CrmAppicationException(e.getMessage());
//        }
//    }
//    @Override
//    @Transactional
//    public Response updateAdmingroup(Integer id, UpdateAdmingroupRequest updateAdmingroupRequest) {
//        try {
//            this.admingroupDataValidator.validateUpdateAdmingroup(updateAdmingroupRequest);
//            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
//            admingroup.update(updateAdmingroupRequest);
//            this.admingroupRepository.saveAndFlush(admingroup);
//            return Response.of(Long.valueOf(admingroup.getId()));
//        } catch (DataIntegrityViolationException e) {
//            throw new CrmAppicationException(e.getMessage());
//        }
//    }
//
//
//    @Override
//    @Transactional
//    public Response blockAdmingrop(Integer id) {
//        try {
//            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
//            admingroup.setValid(false); // Set 'valid' to 0
//            admingroup.setStatus(Status.DELETE); // Or set the appropriate status
//            admingroup.setUpdatedAt(LocalDateTime.now());
//            this.admingroupRepository.saveAndFlush(admingroup);
//            return Response.of(Long.valueOf(id));
//        } catch (DataIntegrityViolationException e) {
//            throw new CrmAppicationException(e.getMessage());
//        }
//    }
//
//    @Override
//    @Transactional
//    public Response unblockAdmingrop(Integer id) {
//        try {
//            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
//            admingroup.setValid(true); // Set 'valid' to 1
//            admingroup.setStatus(Status.DELETE); // Or set the appropriate status
//            admingroup.setUpdatedAt(LocalDateTime.now());
//            this.admingroupRepository.saveAndFlush(admingroup);
//            return Response.of(Long.valueOf(id));
//        } catch (DataIntegrityViolationException e) {
//            throw new CrmAppicationException(e.getMessage());
//        }
//    }
//}
package com.ponsun.san.adminconfiguration.admingroup.services;



import com.ponsun.san.adminconfiguration.admingroup.data.AdmingroupDataValidator;
import com.ponsun.san.adminconfiguration.admingroup.domain.Admingroup;
import com.ponsun.san.adminconfiguration.admingroup.domain.AdmingroupRepository;
import com.ponsun.san.adminconfiguration.admingroup.domain.AdmingroupRepositoryWrapper;
import com.ponsun.san.adminconfiguration.admingroup.request.CreateAdmingroupRequest;
import com.ponsun.san.adminconfiguration.admingroup.request.UpdateAdmingroupRequest;
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
public class AdmingroupWritePlatformServiceImpl implements AdmingroupWritePlatformService {

    private final AdmingroupRepository admingroupRepository;
    private final AdmingroupRepositoryWrapper admingroupRepositoryWrapper;
    private final AdmingroupDataValidator admingroupDataValidator;

    @Transactional
    public Response createAdmingroup(CreateAdmingroupRequest createAdmingroupRequest) {
        try {
            this.admingroupDataValidator.validateSaveAdmingroup(createAdmingroupRequest);
            final Admingroup admingroup = Admingroup.create(createAdmingroupRequest);
            this.admingroupRepository.saveAndFlush(admingroup);
            return Response.of(Long.valueOf(admingroup.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response updateAdmingroup(Integer id, UpdateAdmingroupRequest updateAdmingroupRequest) {
        try {
            this.admingroupDataValidator.validateUpdateAdmingroup(updateAdmingroupRequest);
            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
            admingroup.update(updateAdmingroupRequest);
            this.admingroupRepository.saveAndFlush(admingroup);
                return Response.of(Long.valueOf(admingroup.getId()));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response blockAdmingroup(Integer id) {
        try {
            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
            admingroup.setValid(false);
            admingroup.setStatus(Status.DELETE);
            admingroup.setUpdatedAt(LocalDateTime.now());
            this.admingroupRepository.saveAndFlush(admingroup);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public Response unblockAdmingroup(Integer id) {
        try {
            final Admingroup admingroup = this.admingroupRepositoryWrapper.findOneWithNotFoundDetection(id);
            admingroup.setValid(true);
            admingroup.setStatus(Status.ACTIVE);
            admingroup.setUpdatedAt(LocalDateTime.now());
            this.admingroupRepository.saveAndFlush(admingroup);
            return Response.of(Long.valueOf(id));
        } catch (DataIntegrityViolationException e) {
            throw new EQAS_SAN_ApplicationException(e.getMessage());
        }
    }
}