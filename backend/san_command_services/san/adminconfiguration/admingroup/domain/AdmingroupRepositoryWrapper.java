package com.ponsun.san.adminconfiguration.admingroup.domain;

import com.ponsun.san.adminconfiguration.admingroup.request.AbstractAdmingroupBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdmingroupRepositoryWrapper extends AbstractAdmingroupBaseRequest {
    private final AdmingroupRepository admingroupRepository;

    @Transactional
    public Admingroup findOneWithNotFoundDetection(final Integer id) {
        return this.admingroupRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Admingroup Not found " + id));

    }
    @Override
    public String toString() { return super.toString();}
}
