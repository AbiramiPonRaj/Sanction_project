package com.ponsun.san.aliases.aliasTypeValues.domain;




import com.ponsun.san.aliases.aliasTypeValues.request.AbstractAliasTypeValuesRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AliasTypeValuesRepositoryWrapper extends AbstractAliasTypeValuesRequest {
    private final AliasTypeValuesRepository AliasTypeValuesRepository;


    @Transactional
    public AliasTypeValues findOneWithNotFoundDetection(final Integer id){
        return this.AliasTypeValuesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("AliasTypeValues Not found" + id));
    }

    @Override
    public String toString(){ return super.toString();}
}
