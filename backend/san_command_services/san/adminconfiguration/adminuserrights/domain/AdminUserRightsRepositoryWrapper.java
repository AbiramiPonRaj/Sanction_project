package com.ponsun.san.adminconfiguration.adminuserrights.domain;

import com.ponsun.san.adminconfiguration.adminuserrights.request.AbstractAdminUserRightsBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminUserRightsRepositoryWrapper extends AbstractAdminUserRightsBaseRequest {
    private final AdminUserRightsRepository adminUserRightsRepository;
    @Transactional
    public AdminUserRights findOneWithNotFoundDetection(final Integer id) {
         return  (AdminUserRights) this.adminUserRightsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("AdminUserRights Not Found : " + id));

    }

    @Override
    public String toString() {return super.toString();}
}
