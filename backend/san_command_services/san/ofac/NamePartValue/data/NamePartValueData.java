//package com.ponsun.san.ofac.NamePartValue.data;
//
//import lombok.Data;
//
//@Data
//public class NamePartValueData {
//    private Integer id;
//    private String PrimaryKey;
//    private String NamePartGroupID;
//    private String ScriptID;
//    private String ScriptStatusID;
//    private String Acronym;
//    private String Text;
//    private String FK_DocumentedNamePart;
//
//    public NamePartValueData(Integer id, String primaryKey, String NamePartGroupID, String ScriptID, String ScriptStatusID, String Acronym, String Text, String FK_DocumentedNamePart) {
//        this.id = id;
//        this.PrimaryKey = primaryKey;
//        this. NamePartGroupID = NamePartGroupID;
//        this.ScriptID = ScriptID;
//        this.ScriptStatusID = ScriptStatusID;
//        this.Acronym = Acronym;
//        this.Text = Text;
//        this.FK_DocumentedNamePart = FK_DocumentedNamePart;
//    }
//    public static NamePartValueData newInsatnce(Integer id, String PrimaryKey, String NamePartGroupID, String ScriptID, String ScriptStatusID, String Acronym, String Text, String FK_DocumentedNamePart){
//        return new NamePartValueData(id,PrimaryKey,NamePartGroupID,ScriptID,ScriptStatusID,Acronym,Text,FK_DocumentedNamePart);
//    }
//}
