//package com.ponsun.san.ofac.NamePartValue.domain;
//
//import com.ponsun.san.ofac.NamePartValue.request.AbstractNamePartValueRequest;
//import jakarta.persistence.EntityNotFoundException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.stream.Stream;
//
//@Service
//@RequiredArgsConstructor
//public class NamePartValueWrapper extends AbstractNamePartValueRequest {
//    private final NamePartValueRepository namePartValueRepository;
//    @Transactional
//    public NamePartValue findOneWithNotFoundDetection(final Integer id){
//        return this.namePartValueRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("NamePartValue Not found " + id) );
//    }
//    @Transactional
//    public Stream<NamePartValue> findAllWithNotFoundDetection(){
//        return this.namePartValueRepository.findAll().stream();
//    }
//    @Override
//    public String toString(){
//        return super.toString();
//    }
//}
