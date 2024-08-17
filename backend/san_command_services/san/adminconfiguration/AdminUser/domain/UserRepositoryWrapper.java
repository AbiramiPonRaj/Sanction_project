package com.ponsun.san.adminconfiguration.AdminUser.domain;


import com.ponsun.san.adminconfiguration.AdminUser.request.AbstractUserBaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserRepositoryWrapper extends AbstractUserBaseRequest {

    private final UserRepository userRepository;

    @Transactional
    public User findOneWithNotFoundDetection(final Integer id) {
        return this.userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User Not found " + id));
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
