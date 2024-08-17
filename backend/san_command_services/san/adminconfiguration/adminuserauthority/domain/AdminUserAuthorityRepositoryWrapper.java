package com.ponsun.san.adminconfiguration.adminuserauthority.domain;

import com.ponsun.san.adminconfiguration.adminuserauthority.request.AbstractAdminUserAuthorityBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserAuthorityRepositoryWrapper extends AbstractAdminUserAuthorityBaseRequest {

    private final AdminUserAuthorityRepository adminUserAuthorityRepository;

    @Transactional
    public AdminUserAuthority findOneWithNotFoundDetection(final Integer id) {
        return (AdminUserAuthority) this.adminUserAuthorityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AdminUserAuthority Not Found: " + id));
    }
    @Override
    public String toString() {return super.toString();}
}

