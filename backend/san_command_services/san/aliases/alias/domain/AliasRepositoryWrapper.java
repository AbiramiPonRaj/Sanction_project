package com.ponsun.san.aliases.alias.domain;




import com.ponsun.san.aliases.alias.request.AbstractAliasRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AliasRepositoryWrapper extends AbstractAliasRequest {
    private final AliasRepository AliasRepository;


    @Transactional
    public Alias findOneWithNotFoundDetection(final Integer id){
        return this.AliasRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Alias Not found" + id));
    }

    @Override
    public String toString(){ return super.toString();}
}
