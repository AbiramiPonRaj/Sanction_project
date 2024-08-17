package com.ponsun.san.master.status.domain;

import com.ponsun.san.master.status.request.AbstractStatusBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StatusRepositoryWrapper extends AbstractStatusBaseRequest {
    private final StatusRepository statusRepository;

    @Transactional
    public Status findOneWithNotFoundDetection(final Integer id) {
        return this.statusRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Status Not found " + id));
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

