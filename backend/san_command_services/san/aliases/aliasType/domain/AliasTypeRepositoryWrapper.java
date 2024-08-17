package com.ponsun.san.aliases.aliasType.domain;


import com.ponsun.san.aliases.aliasType.request.AbstractAliasTypeRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AliasTypeRepositoryWrapper extends AbstractAliasTypeRequest {
    private final AliasTypeRepository aliasTypeRepository;


    @Transactional
    public AliasType findOneWithNotFoundDetection(final Integer id){
        return this.aliasTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AliasType Not found" + id));
    }

    @Override
    public String toString(){ return super.toString();}
}
