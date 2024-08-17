package com.ponsun.san.adminconfiguration.resetpassword.domain;

import com.ponsun.san.adminconfiguration.resetpassword.request.AbstractResetPasswordBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ResetPasswordRepositoryWrapper extends AbstractResetPasswordBaseRequest {
    private final ResetPasswordRepository resetpasswordRepository;

    @Transactional
    public ResetPassword findOneWithNotFoundDetection(final Integer id) {
        return this.resetpasswordRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ResetPassword Not found " + id));
    }

    @Override
    public String toString() {
        return super.toString();
    }


}
