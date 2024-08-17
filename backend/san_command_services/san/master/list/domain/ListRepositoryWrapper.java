package com.ponsun.san.master.list.domain;




import com.ponsun.san.master.list.request.AbstractListRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ListRepositoryWrapper extends AbstractListRequest {
    private final ListRepository listRepository;


    @Transactional
    public Lists findOneWithNotFoundDetection(final Integer id){
        return this.listRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("List Not found" + id));
    }

    @Override
    public String toString(){ return super.toString();}
}
