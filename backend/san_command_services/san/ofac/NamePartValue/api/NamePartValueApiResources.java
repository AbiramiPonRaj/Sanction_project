//package com.ponsun.san.ofac.NamePartValue.api;
//
//import com.ponsun.san.ofac.NamePartValue.domain.NamePartValue;
//import com.ponsun.san.ofac.NamePartValue.services.NamePartValueReadService;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//@RequestMapping("api/v1/namepartvalue")
//@Tag(name="NamePartValueApiResources")
//public class NamePartValueApiResources {
//    private final NamePartValueReadService namePartValueReadService;
//
//    @GetMapping("/{id}")
//    public NamePartValue fetchNamePartValueById(@PathVariable(name = "id")Integer id) {
//        return this.namePartValueReadService.fetchNamePartValueById(id);
//    }
//
//    @GetMapping
//    public List<NamePartValue> fetchAll(){return this.namePartValueReadService.fetchAllNamePartValue();}
//}
//
