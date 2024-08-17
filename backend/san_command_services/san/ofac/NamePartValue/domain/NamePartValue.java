//package com.ponsun.san.ofac.NamePartValue.domain;
//
//import com.ponsun.san.infrastructure.baseentity.BaseEntity;
//import com.ponsun.san.ofac.NamePartValue.request.CreateNamePartValueRequest;
//import com.ponsun.san.ofac.NamePartValue.request.UpdateNamePartValueRequest;
//import jakarta.persistence.*;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "namepartvalue")
//public class NamePartValue extends BaseEntity {
//    private static final long serialVersionUID = 1L;
//
////    @Id
////    @Column(name = "id", nullable = false)
////    @GeneratedValue(strategy = GenerationType.IDENTITY)
////    private Integer id;
////
////    @Column(name = "PrimaryKey")
//    @Id
//    @Column(name = "PrimaryKey", nullable = false)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer PrimaryKey;
//
//    @Column(name = "NamePartGroupID")
//    private String NamePartGroupID;
//
//    @Column(name = "ScriptID")
//    private String ScriptID;
//
//    @Column(name = "ScriptStatusID")
//    private String ScriptStatusID;
//
//    @Column(name = "Acronym")
//    private String Acronym;
//
//    @Column(name = "Text")
//    private String Text;
//
//    @Column(name = "FK_DocumentedNamePart")
//    private String FK_DocumentedNamePart;
//
//    public static NamePartValue create(CreateNamePartValueRequest request) {
//        final NamePartValue namePartValue = new NamePartValue();
//        //namePartValue.setId(request.getId());
//        namePartValue.setPrimaryKey(request.getPrimaryKey());
//        namePartValue.setNamePartGroupID(request.getNamePartGroupID());
//        namePartValue.setScriptID(request.getScriptID());
//        namePartValue.setScriptStatusID(request.getScriptStatusID());
//        namePartValue.setAcronym(request.getAcronym());
//        namePartValue.setText(request.getText());
//        namePartValue.setFK_DocumentedNamePart(request.getFK_DocumentedNamePart());
////        namePartValue.onCreate();
////        namePartValue.setStatus(Status.ACTIVE);
//        return namePartValue;
//    }
//
//    public void update(final UpdateNamePartValueRequest request) {
//        this.setPrimaryKey(request.getPrimaryKey());
//        this.setNamePartGroupID(request.getNamePartGroupID());
//        this.setScriptID(request.getScriptID());
//        this.setScriptStatusID(request.getScriptStatusID());
//        this.setAcronym(request.getAcronym());
//        this.setText(request.getText());
//        this.setFK_DocumentedNamePart(request.getFK_DocumentedNamePart());
////        this.onUpdate();
////        this.setStatus(Status.ACTIVE);
//    }
//}