//package com.ponsun.san.ofac.NamePartValue.services;
//
//import com.google.common.collect.Lists;
//import com.ponsun.san.dto.RecordDTO;
//import com.ponsun.san.dto.SearchDTO;
//import com.ponsun.san.ofac.NamePartValue.domain.NamePartValue;
//import com.ponsun.san.ofac.NamePartValue.domain.NamePartValueRepository;
//import com.ponsun.san.ofac.NamePartValue.domain.NamePartValueWrapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Stream;
//
//@Service
//@RequiredArgsConstructor
//@Slf4j
//public class NamePartValueReadServiceImpl implements NamePartValueReadService{
//    private final NamePartValueWrapper namePartValueWrapper;
//    private final JdbcTemplate jdbcTemplate;
//    private final NamePartValueRepository namePartValueRepository;
//
//    @Override
//    public NamePartValue fetchNamePartValueById(Integer id){
//        return this.namePartValueRepository.findById(id).get();
//    }
//    @Override
//    public List<NamePartValue> fetchAllNamePartValue(){
//        return this.namePartValueRepository.findAll();
//    }
//
//    @Override
//    public List<RecordDTO> fetchAllCustomerList(SearchDTO searchDTO) {
//        searchDTO.getName();
//        ArrayList<Stream<NamePartValue>> customerPage = Lists.newArrayList(namePartValueWrapper.findAllWithNotFoundDetection());
//        return null;
//    }
//
//}
